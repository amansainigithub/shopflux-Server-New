package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
public class ProductSubCategoryForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long productSubCategoryId;

    @Column(unique = true,nullable = false)
    private String subCategoryName;

    @Column(length = 1000)
    private String description;

    @Column(length = 2500)
    private String shortDescription;

    @Column(length = 5000)
    private String longDescription;

    private String imageUrl;

    private String videoUrl;

    @Column(columnDefinition="tinyint(1) default 1")
    private boolean subCategoryStatus;

    @Basic(optional = false)
    @Column( insertable = false, updatable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationOwnTime;

    @Basic(optional = false)
    @Column( insertable = false, updatable = false,name="USER_CREATING_TIME",columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date userCreatingTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private ProductRootCategoryForm productRootCategoryForm;

    @OneToMany(mappedBy = "productSubCategoryForm",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<ProductForm> productCategoryForm;

}
