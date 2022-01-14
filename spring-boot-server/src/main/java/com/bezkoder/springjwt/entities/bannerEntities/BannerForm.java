package com.bezkoder.springjwt.entities.bannerEntities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Data
@Entity
@Table(name = "banner")
public class BannerForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bannerId;

    @NotBlank(message = "Banner Name not be blank")
    private String bannerName;

    private String defaultName;

    @Column(length = 500)
    private String description;

    @Column(length = 1000)
    private String shortDescription;

    @Column(length = 2500)
    private String longDescription;

    private String filename;

    @Column(length = 1000)
    private String fileUrl;

    private String fileSize;

    private String fileContentType;

    private String folderName;




}
