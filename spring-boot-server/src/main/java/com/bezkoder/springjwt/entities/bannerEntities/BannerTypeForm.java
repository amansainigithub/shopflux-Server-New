package com.bezkoder.springjwt.entities.bannerEntities;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "bannerType")
@Data
public class BannerTypeForm {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 2000)
    private String bannerTypeName;
    @Column(length = 2000)
    private String bannerTypeDesc;
    @Column(length = 3000)
    private String bannerLongDesc;

}
