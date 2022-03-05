package com.bezkoder.springjwt.publicAllowance.publicController.userController;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.publicAllowance.publicServices.UserServicePublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.UserUrlMappingPublic;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class UserControllerPublic {

    @Autowired
    private UserServicePublic userServicePublic;


    @GetMapping(UserUrlMappingPublic.GET_USER_ADDRESS_PUBLIC)
    public ResponseEntity<?> getUserAddress(@PathVariable String userName) throws InterruptedException, RazorpayException {

        String addressResult = this.userServicePublic.getUserAddress(userName);
        if(addressResult != null)
        {
            return   ResponseEntity.ok(new MessageResponse(addressResult));
        }
        else {
            return   ResponseEntity.ok(new MessageResponse(addressResult));
        }

    }




}
