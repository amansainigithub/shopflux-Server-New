package com.bezkoder.springjwt.paymentController.razorpay.razorpayService;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class RazorPayService {


    public Object createRazorPayOrder(Map<String, String> data) throws RazorpayException {

         String amount   =   data.get("amount");
        String currency  = data.get("currency");
        String receipt  = data.get("receipt");

        System.out.println(amount+" : "+currency +" : "+ receipt);

        RazorpayClient razorpay = new RazorpayClient("rzp_test_YdBfMCwPkwhqi3", "aNHb9Wm4g5XSjfYgrXoZUlpi");

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", Integer.parseInt(amount)*100); // amount in the smallest currency unit
        orderRequest.put("currency", currency);
        orderRequest.put("receipt", receipt);

        Order order = razorpay.Orders.create(orderRequest);
        System.out.println("***********************************");
       String amount_paid =  order.get("amount-paid").toString();
        String amount_due =  order.get("amount_due").toString();
        System.out.println(order.toString());
        System.out.println("AMOUNT PAID : "+amount_paid);
        System.out.println("AMOUNT_DUE : "+amount_due);
        return order;
    }
}
