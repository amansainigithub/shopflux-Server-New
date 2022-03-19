package com.bezkoder.springjwt.entities.cartEntities;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "cartCatcher")
public class CartCatcherForm {

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
    private String productSize;

    private Date currrentDate = new Date();

}