package com.bezkoder.springjwt.bucket.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "bucketHub")
public class BucketForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long bucketId;

    //[Product or product-category Name or product-category Id]
    private String pcId;

    //[Product Category Name ,root,sub,product]
    private String pcName;

    @Column(length = 2000)
    private String fileUrl;

    @Column(length = 2000)
    private String folderPath;

    @Column(length = 2000)
    private String imageName;

    private String imageSize;

    private String folderName;

    private String isLinking;

    private String imageType;

    private String createdDate;

    private String createdTime;



}
