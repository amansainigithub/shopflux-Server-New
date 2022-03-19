package com.bezkoder.springjwt.payload.request;

import lombok.Data;

@Data
public class OtpVerify {

    private String otp;
    private String email;
}
