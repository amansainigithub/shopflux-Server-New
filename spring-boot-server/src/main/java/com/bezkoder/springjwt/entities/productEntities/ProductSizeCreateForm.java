package com.bezkoder.springjwt.entities.productEntities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class ProductSizeCreateForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String productSize;

    private String  defaultName;

    @Column(length = 3000)
    private String description;

    @Column(length = 5000)
    private String longDescription;

    private String status;

}
