package com.bezkoder.springjwt.paymentController.razorpay.razorpayModels;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class RazorPayOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String amount;

    private String amountPaid;
    private String notes;
    private String createdAt;
    private String amountDue;
    private String currency;
    private String receipt;
    private String orderId;
    private String entity;
    private String offerId;
    private String status;
    private String attempts;
    private String userName;
    private String userId;

    private String razorpayPaymentId;
    private String razorpaySignatureId;

}
