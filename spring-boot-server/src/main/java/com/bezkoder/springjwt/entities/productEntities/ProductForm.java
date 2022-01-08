package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Column(unique = true)
    private String productName;

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
    private String  savePricePercentage;
    private String  sellPrice;
    private String  shortDescription;
    private String subCategoryId;
    private String  productLength;
    private String  productWidth;
    private String productHeight;
    private String productWeight;

    private String sizeXS;
    private String sizeS;
    private String sizeM;
    private String sizeL;
    private String sizeXL;
    private String sizeXXL;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean isEnabled;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "productForm",fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ProductFileUrls> productFileUrls;

    @ManyToOne
    private ProductSubCategoryForm productSubCategoryForm;

    private String creatingDate;

    private String lastUpdatedDate;




}
