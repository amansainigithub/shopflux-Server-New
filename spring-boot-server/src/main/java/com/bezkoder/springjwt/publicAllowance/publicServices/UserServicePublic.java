package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.entities.addressEntities.UpdateAddressForm;
import com.bezkoder.springjwt.entities.userEntities.ChangePassword;
import com.bezkoder.springjwt.entities.userEntities.UserEntity;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class UserServicePublic {
    @Autowired
    private CartImpleServicePublic cartImpleServicePublic;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


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

    public UpdateAddressForm getCurrentUserAddress(String userName,HttpServletRequest request) {
        UpdateAddressForm updateAddressForm=null;
        try {
            Principal principal = request.getUserPrincipal();
            if (principal.getName() == null) {
                throw new UsernameNotFoundException("User Not Found !");
            }
           User data = this.userRepository.findByUsername(principal.getName()).get();
            if ( data.getUsername().equals(userName)) {
                updateAddressForm = new UpdateAddressForm();
                updateAddressForm.setAddressLine1(data.getAddressLine1());
                updateAddressForm.setAddressLine2(data.getAddressLine2());
                updateAddressForm.setCity(data.getCity());
                updateAddressForm.setState(data.getState());
                updateAddressForm.setZipCode(data.getZipCode());
                updateAddressForm.setCountry(data.getCountry());
                updateAddressForm.setMobile(data.getMobile());
                return updateAddressForm;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return updateAddressForm;
    }

    public UserEntity getCurrentUser(String userName,HttpServletRequest request) {
        UserEntity userEntity=null;
        try {
            Principal principal = request.getUserPrincipal();
            if (principal.getName() == null) {
                throw new UsernameNotFoundException("User Not Found !");
            }
            User data = this.userRepository.findByUsername(principal.getName()).get();

         if(data.getUsername().equals(userName))
         {
             userEntity =  new UserEntity();
             userEntity.setUsername(data.getUsername());
             userEntity.setEmail(data.getEmail());
             userEntity.setGender(data.getGender());
             userEntity.setMobile(data.getMobile());
         }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userEntity;
    }

    public UserEntity updateCurrentUserProfile(UserEntity userEntity, HttpServletRequest request) {
        try {
            Principal principal = request.getUserPrincipal();
            if (principal.getName() == null) {
                throw new UsernameNotFoundException("User Not Found !");
            }
            User data = this.userRepository.findByUsername(principal.getName()).get();

            if(data.getUsername().equals(userEntity.getUsername()))
            {
                data.setMobile(userEntity.getMobile());
                data.setUsername(userEntity.getUsername());
                data.setGender(userEntity.getGender());
                this.userRepository.save(data);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return userEntity;
    }

    public boolean changePasswordCurrentUser(ChangePassword changePassword, HttpServletRequest request) {
        System.out.println(changePassword);
        boolean flag=false;
        try {

            if(!changePassword.getNewPassword().equals(changePassword.getConformPassword()))
            {
                throw new Exception("Password Not Matched");
            }

            Principal principal = request.getUserPrincipal();
            if (principal.getName() == null) {
                throw new UsernameNotFoundException("User Not Found !");
            }
            User user =  this.userRepository.findByUsername(changePassword.getCurrentUser()).get();

            if(this.checkCurrentUserNameAndPassword(changePassword,user))
            {
                user.setPassword(this.bCryptPasswordEncoder.encode(changePassword.getNewPassword()));
                this.userRepository.save(user);
                flag = true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean checkCurrentUserNameAndPassword(ChangePassword changePassword,User user) throws Exception {
        if(user.getUsername().equals(changePassword.getCurrentUser())
            && this.bCryptPasswordEncoder.matches(changePassword.getCurrentPassword(),user.getPassword()))
        {
            System.out.println("user and password both are matched");
            return true;
        }
        {
          throw new Exception("check Current UserName And Password");
        }
    }
}
