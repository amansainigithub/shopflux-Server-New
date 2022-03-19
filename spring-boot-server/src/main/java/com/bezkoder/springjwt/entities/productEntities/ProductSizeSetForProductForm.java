package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "productSizeSetForProduct")
@Data
public class ProductSizeSetForProductForm {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productSize;

    private String productSizeName;

    private String productSizeQuantity;

    @Column(length = 2500)
    private String shortDescription;

    @Column(length = 5000)
    private String longDescription;

    private String sequenceNum;


    private String status;

    @ManyToOne
    @JsonBackReference
    private ProductForm productForm;
}
