package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayService.RazorPayService;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.CartInterfacePublic;
import com.bezkoder.springjwt.repositories.cartRepo.CartPaidRepo;
import com.bezkoder.springjwt.repositories.cartRepo.CartRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartImpleServicePublic implements CartInterfacePublic {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartPaidRepo cartPaidRepo;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RazorPayService razorPayService;

    public int totalPrice =0;

    @Override
    public Order cartCatcherService(List<CartCatcherForm> cartCatcherList, HttpServletRequest request) {

        Order order = null;
        try {
                   int totalPrice =   this.saveCartCatcher(cartCatcherList,request);
               //System.out.println("********************************");
               //System.out.println(totalPrice);

           //CREATE RAZORPAY ORDER
            if(totalPrice > 0)
              {
                  //System.out.println("inner if running");
                  Map<String,String> nodeMap = new HashMap<>();
                  nodeMap.put("amount",String.valueOf(totalPrice));
                  nodeMap.put("currency","INR");
                  nodeMap.put("receipt","txn_100100");
                  order =  this.razorPayService.createRazorPayOrder(nodeMap,request);
                  System.out.println(order.toString());

              }
       }
       catch (Exception e)
       {
            e.printStackTrace();

       }
       return order;
    }



    public int saveCartCatcher(List<CartCatcherForm> cartCatcherList, HttpServletRequest request)
    {
            this.totalPrice=0;

          try {
              //GET CURRENT USER
              User user =      this.getCurrentUser(request);
              String userName =   user.getUsername();
              long id =   user.getId();

              for(CartCatcherForm cartCatcherForm : cartCatcherList)
              {
                  this.totalPrice = this.totalPrice +
                                      Integer.parseInt(cartCatcherForm.getProductPrice()) *
                                              Integer.parseInt(cartCatcherForm.getProductQuantity());

                  cartCatcherForm.setStatus("NOT_PAID");
                  cartCatcherForm.setUserId(String.valueOf(id));
                  cartCatcherForm.setUserName(userName);
                  cartCatcherForm.setStatus("NOT_PAID");
                  this.cartRepository.save(cartCatcherForm);
              }
          }
          catch (Exception e)
          {
              e.printStackTrace();
          }

            return this.totalPrice;
    }




    //UPDATE CART CATCHER
    @Override
    public boolean updateCartCatcher(List<CartCatcherPaidForm> cartCatcherPaidForms, HttpServletRequest request,String orderId,String paymentId) {
        boolean flag=false;
        try {
               if( updateAndSaveCartCatcher(cartCatcherPaidForms,request,orderId,paymentId))
               {
                  flag=true;
               }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean updateAndSaveCartCatcher(List<CartCatcherPaidForm> cartCatcherPaidForms, HttpServletRequest request ,String orderId,String paymentId)
    {
       boolean flag= false;

        try {
            //GET CURRENT USER
            User user =      this.getCurrentUser(request);
            String userName =   user.getUsername();
            long id =   user.getId();

            for(CartCatcherPaidForm cartCatcherPaidForm : cartCatcherPaidForms)
            {

                cartCatcherPaidForm.setStatus("PAID");
                cartCatcherPaidForm.setUserId(String.valueOf(id));
                cartCatcherPaidForm.setUserName(userName);
                cartCatcherPaidForm.setAddress(user.getAddressLine1());
                cartCatcherPaidForm.setMobile(user.getMobile());
                cartCatcherPaidForm.setRazorOrderId(orderId);
                cartCatcherPaidForm.setRazorPaymentId(paymentId);
                this.cartPaidRepo.save(cartCatcherPaidForm);
                System.out.println("*********************************************");
                System.out.println("cart catcher update successfully");
                System.out.println("*********************************************");

            }
            flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return flag;

    }





    public User getCurrentUser(HttpServletRequest request)
    {
        Principal principal   =  request.getUserPrincipal();
        return this.userRepository.findByUsername(principal.getName().toString()).get();
    }
}
