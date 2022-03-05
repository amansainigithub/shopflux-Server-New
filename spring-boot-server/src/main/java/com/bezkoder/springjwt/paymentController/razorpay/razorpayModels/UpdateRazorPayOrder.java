package com.bezkoder.springjwt.paymentController.razorpay.razorpayModels;

import lombok.Data;

@Data
public class UpdateRazorPayOrder {

    private String razorpayPaymentId;
    private String razorpayOrderId;
    private String razorpaySignatureId;
}
