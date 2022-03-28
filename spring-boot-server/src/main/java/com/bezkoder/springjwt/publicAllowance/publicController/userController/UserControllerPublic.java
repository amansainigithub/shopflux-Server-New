package com.bezkoder.springjwt.publicAllowance.publicController.userController;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import com.bezkoder.springjwt.entities.userEntities.ChangePassword;
import com.bezkoder.springjwt.entities.userEntities.UserEntity;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.publicAllowance.publicServices.UserServicePublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.UserUrlMappingPublic;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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


    @GetMapping(UserUrlMappingPublic.GET_CURRENT_USER)
    public ResponseEntity<?> getCurrentUser(@PathVariable String userName,HttpServletRequest request) throws InterruptedException, RazorpayException {

        UserEntity userApproxData = this.userServicePublic.getCurrentUser(userName,request);
        if(userApproxData != null)
        {
            return   ResponseEntity.ok(userApproxData);
        }
        else {
            return   ResponseEntity.ok(HttpStatus.BAD_GATEWAY);
        }

    }


    @PostMapping(UserUrlMappingPublic.UPDATE_CURRENT_USER_PROFILE)
    public ResponseEntity<?> updateCurrentUserProfile(@RequestBody UserEntity userEntity, HttpServletRequest request) throws InterruptedException, RazorpayException {

        UserEntity userApproxData = this.userServicePublic.updateCurrentUserProfile(userEntity,request);
        if(userApproxData != null)
        {
            return   ResponseEntity.ok(userApproxData);
        }
        else {
            return   ResponseEntity.ok(HttpStatus.BAD_GATEWAY);
        }

    }


    @PostMapping(UserUrlMappingPublic.CHANGE_PASSWORD_CURRENT_USER)
    public ResponseEntity<?> changePasswordCurrentUser(@Valid  @RequestBody ChangePassword changePassword, HttpServletRequest request) throws InterruptedException, RazorpayException {

        boolean result = this.userServicePublic.changePasswordCurrentUser(changePassword,request);
        if(result)
        {
            return   ResponseEntity.ok(new MessageResponse("Password Change Success"));
        }
        else {
            return   ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }




}
