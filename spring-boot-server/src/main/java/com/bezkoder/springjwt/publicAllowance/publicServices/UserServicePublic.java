package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.entities.addressEntities.UpdateAddressForm;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class UserServicePublic {
    @Autowired
    private CartImpleServicePublic cartImpleServicePublic;

    @Autowired
    private UserRepository userRepository;


    public String getUserAddress(String userName) {
            String result = null ;
        try {
            User user  =  this.userRepository.findByUsername(userName.trim()).get();

            if(user.getAddressLine1() == null || user.getAddressLine1().isEmpty() )
            {

            }
            else
            {
               result = user.getAddressLine1();
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    public User updateCurrentUserAddress(UpdateAddressForm updateAddressForm, HttpServletRequest request) {
           User user =null;
            try {
                    User currentUser  =     this.cartImpleServicePublic.getCurrentUser(request);
                   currentUser.setAddressLine1(updateAddressForm.getAddressLine1());
                   currentUser.setAddressLine2(updateAddressForm.getAddressLine2());
                   currentUser.setCity(updateAddressForm.getCity());
                   currentUser.setState(updateAddressForm.getState());
                   currentUser.setZipCode(updateAddressForm.getZipCode());
                   currentUser.setCountry(updateAddressForm.getCountry());
                   currentUser.setMobile(updateAddressForm.getMobile());

                  user =   this.userRepository.save(currentUser);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return user;

    }
}
