package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class ProductFileUrls {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long urlId;

    @Column(length = 5000)
    private String fileUrl;

    private long bucketId;

    private String productId;

    @ManyToOne
    @JsonBackReference
    private ProductForm productForm;


}
