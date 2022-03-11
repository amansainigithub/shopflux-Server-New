package com.bezkoder.springjwt.publicAllowance.publicController.cartController;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.publicAllowance.publicServices.CartImpleServicePublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.repository.UserRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class CartController {


    @Autowired
    private CartImpleServicePublic cartImpleServicePublic;

    @PostMapping("/cartCatcher")
    public ResponseEntity<?> cartCatcher(@RequestBody List<CartCatcherForm> cartCatcher, HttpServletRequest request) throws InterruptedException, RazorpayException {

        Order order =  this.cartImpleServicePublic.cartCatcherService(cartCatcher,request);
            if(order != null)
            {
                  return   ResponseEntity.ok(order.toString());
            }
            else {
                  return   ResponseEntity.ok(new MessageResponse("FAILED"));
            }

      }

    @PostMapping("/updateCartCatcher/{orderId}/{paymentId}")
    public ResponseEntity<?> updateCartCatcher(@RequestBody List<CartCatcherPaidForm> cartCatcherPaidForms,
                                               @PathVariable String orderId,
                                               @PathVariable String paymentId,
                                               HttpServletRequest request) throws InterruptedException, RazorpayException {
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("handler method running......");
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        boolean result =  this.cartImpleServicePublic.updateCartCatcher(cartCatcherPaidForms,request,orderId,paymentId);
        if(result)
        {
            return   ResponseEntity.ok(new MessageResponse("success"));
        }
        else {
            return   ResponseEntity.ok(new MessageResponse("FAILED"));
        }

    }





}
