package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ProductForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productId;

    private String productName;
    private String productDefaultName;
    private String  description;
    private String  discount;
    private boolean  discountAvailable=false;
    private String  longDescription;
    private String  manufacturingPrice;
    private String  mrpPrice;
    private String  note;
    private String  productAvailable;
    private String  productDetails;
    private String  productLongDetails;
    private String  productShortDetails;
    private String  productUSN;
    private String  productSKU;
    private String   quantity;
    private String  rootCategoryId;
    private String  savePrice;
    private String thumbnailUrl;
    private String videoThumbnailUrl;
    private String  savePricePercentage;
    private String  sellPrice;
    private String  shortDescription;
    private String subCategoryId;
    private String finalCategoryId;
    private String  productLength;
    private String  productWidth;
    private String productHeight;
    private String productWeight;
    private String color;


    private String sizeXS;
    private String sizeS;
    private String sizeM;
    private String sizeL;
    private String sizeXL;
    private String sizeXXL;
    private String sizeXXXL;

    private String uk4;
    private String uk5;
    private String uk6;
    private String uk7;
    private String uk8;
    private String uk9;
    private String uk10;


    @Column(columnDefinition="tinyint(1) default 1")
    private boolean productStatus;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productForm")
    @JsonManagedReference
    private List<ProductFileUrls> productFileUrls;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductFinalCateogoryForm productFinalCategoryForm;

    private String creatingDate;

    private String lastUpdatedDate;


    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productForm")
    @JsonManagedReference
    private List<ProductSizeSetForProductForm> productSizeSetForProductForms;




}
