package com.bezkoder.springjwt.publicAllowance.publicController.userController;

import com.bezkoder.springjwt.entities.addressEntities.UpdateAddressForm;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.publicAllowance.publicServices.UserServicePublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.UserUrlMappingPublic;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class UpdateUserAddressController {

    @Autowired
    private UserServicePublic userServicePublic;

    @PostMapping(UserUrlMappingPublic.UPDATE_USER_ADDRESS_PUBLIC)
    public ResponseEntity<?> updateCurrentUserAddress(@RequestBody UpdateAddressForm updateAddressForm , HttpServletRequest request) throws InterruptedException, RazorpayException {

        User  user = this.userServicePublic.updateCurrentUserAddress(updateAddressForm,request);
        if(user != null)
        {
            return   ResponseEntity.ok(user);
        }
        else {
            return   ResponseEntity.ok(user);
        }

    }

}
