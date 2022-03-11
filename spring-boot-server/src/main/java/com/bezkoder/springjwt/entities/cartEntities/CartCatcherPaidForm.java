package com.bezkoder.springjwt.entities.cartEntities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cartCatcherPaid")
public class CartCatcherPaidForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String productId;
    private String productName;
    private String productPrice;
    private String productQuantity;
    private String url;
    private String userName;
    private String userId;
    private String status;
    private String address;
    private String mobile;

    private String razorOrderId;
    private String razorPaymentId;
    private String razorSignatureId;

    private String deliveryDate;
    private String deliveryDateFormat;

    private String deliveryOrderId;
    private String deliveryOrderCompanyName;

    @Column(columnDefinition = "varchar(255) default 'N'")
    private String deliveryStatus;

    private Date currrentDate = new Date();
}
