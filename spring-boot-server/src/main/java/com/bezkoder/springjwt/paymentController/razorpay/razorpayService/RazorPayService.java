package com.bezkoder.springjwt.paymentController.razorpay.razorpayService;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.RazorPayOrder;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.UpdateRazorPayOrder;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayRepo.RazorpayOrderRepo;
import com.bezkoder.springjwt.publicAllowance.publicServices.CartImpleServicePublic;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springfox.documentation.spring.web.json.Json;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Service
public class RazorPayService {


    @Autowired
    private RazorpayOrderRepo razorpayOrderRepo;

    @Autowired
    private CartImpleServicePublic cartImpleServicePublic;

    private final String razorpay_key="rzp_test_YdBfMCwPkwhqi3";
    private final String razorpay_secret="aNHb9Wm4g5XSjfYgrXoZUlpi";


    public Order createRazorPayOrder(Map<String, String> data, HttpServletRequest request) throws RazorpayException {

         String amount    =    data.get("amount");
         String currency  =    data.get("currency");
         String receipt   =    data.get("receipt");

       // System.out.println(amount+" : "+currency +" : "+ receipt);

        RazorpayClient razorpay = new RazorpayClient(razorpay_key, razorpay_secret);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", Integer.parseInt(amount)*100); // amount in the smallest currency unit
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);
        //CREATE ORDER
        Order order = razorpay.Orders.create(orderRequest);

        //**SAVE RAZORPAY ORDER**;
        if(this.saveRazorpayOrder(order,request) != null)
        {
            System.out.println("**SAVE-ORDER**");
        }

        return order;
    }


    public RazorPayOrder saveRazorpayOrder( Order order , HttpServletRequest request )
    {
        System.out.println("********************************");
        System.out.println(order.get("amount").toString());

            RazorPayOrder rpo = new RazorPayOrder();
            rpo.setAmount(order.get("amount").toString());
            rpo.setCurrency(order.get("currency").toString());
            rpo.setReceipt(order.get("receipt").toString());
            rpo.setOrderId(order.get("id").toString());
            rpo.setStatus(order.get("status").toString());

            //SET USER DATA TO RAZOR-PAY ORDER OBJ..
           User user  =  this.cartImpleServicePublic.getCurrentUser(request);
           rpo.setUserName(user.getUsername());
           rpo.setUserId(String.valueOf(user.getId()));

           //SAVE USER
           return  this.razorpayOrderRepo.save(rpo);
    }


    public RazorPayOrder updateRazorPayOrderService(UpdateRazorPayOrder updateRazorPayOrder)
    {   RazorPayOrder rpo =null;
            try {
                System.out.println("running 2");
                  RazorPayOrder razorPayOrder =   this.razorpayOrderRepo.
                                                    findByOrderId(updateRazorPayOrder.getRazorpayOrderId().trim());
                  if(razorPayOrder != null )
                  {
                      razorPayOrder.setStatus("PAID");
                      razorPayOrder.setRazorpayPaymentId(updateRazorPayOrder.getRazorpayPaymentId());
                      razorPayOrder.setRazorpaySignatureId(updateRazorPayOrder.getRazorpaySignatureId());
                      rpo  =   this.razorpayOrderRepo.save(razorPayOrder);
                  }

            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return rpo;
    }


    public RazorPayOrder razorpayGetOrderDetailsByOrderId(String orderId) {

        RazorPayOrder razorPayOrder=null;
       try {
           RazorpayClient razorpay = new RazorpayClient(razorpay_key, razorpay_secret);
           Order order = razorpay.Orders.fetch(orderId);
            razorPayOrder=new RazorPayOrder();
            razorPayOrder.setAmount(order.get("amount").toString());
           razorPayOrder.setAmountPaid(order.get("amount_paid").toString());
           razorPayOrder.setAmountDue(order.get("amount_due").toString());
           razorPayOrder.setCurrency(order.get("currency"));
           razorPayOrder.setReceipt(order.get("receipt"));
           razorPayOrder.setOrderId(order.get("orderId"));
           razorPayOrder.setReceipt(order.get("receipt"));
           razorPayOrder.setStatus(order.get("status"));
           razorPayOrder.setAttempts(order.get("attempts").toString());

       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return razorPayOrder;
    }
}
