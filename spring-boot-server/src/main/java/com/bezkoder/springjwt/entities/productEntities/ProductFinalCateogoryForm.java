package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ProductFinalCateogoryForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productFinalCategoryId;

    @Column(unique = true,nullable = false)
    private String finalCategoryName;

    @Column(length = 1000)
    private String description;

    @Column(length = 2500)
    private String shortDescription;

    @Column(length = 5000)
    private String longDescription;

    private String imageUrl;

    private String videoUrl;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean finalCategoryStatus;

    @Basic(optional = false)
    @Column( insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationOwnTime;

    @Basic(optional = false)
    @Column( insertable = false, updatable = false,name="USER_CREATING_TIME",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreatingTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductSubCategoryForm productSubCategoryForm;

    @OneToMany(mappedBy = "productFinalCategoryForm",cascade = CascadeType.ALL)
    @JsonBackReference
    private List<ProductForm> productCategoryForm;
}
