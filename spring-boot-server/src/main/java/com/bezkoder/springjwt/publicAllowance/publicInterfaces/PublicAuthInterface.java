package com.bezkoder.springjwt.publicAllowance.publicInterfaces;

import com.bezkoder.springjwt.payload.request.ChangePasswordForm;

import javax.servlet.http.HttpServletRequest;

public interface PublicAuthInterface {

    boolean existsBYUserEmail(String userEmail, HttpServletRequest request);
    boolean changePasswordByEmailAndOtp(ChangePasswordForm changePasswordForm, HttpServletRequest request);
}
