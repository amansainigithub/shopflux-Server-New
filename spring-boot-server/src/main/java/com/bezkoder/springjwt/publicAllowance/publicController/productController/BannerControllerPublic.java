package com.bezkoder.springjwt.publicAllowance.publicController.productController;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.publicAllowance.publicServices.BannerImpleService;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.BannerUrlMappingPublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.ProductUrlMappingPublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class BannerControllerPublic {

    @Autowired
    private  BannerImpleService bannerImpleService;


    @GetMapping(BannerUrlMappingPublic.GET_BANNER_LIST)
    public ResponseEntity<?> getBannerList() throws InterruptedException {

        List<BannerForm> list = this.bannerImpleService.getBannerList();
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(BannerUrlMappingPublic.GET_TOP_BANNER_LIST_PUBLIC)
    public ResponseEntity<?> getTopBannerList_public() throws InterruptedException {

        List<BannerForm> list = this.bannerImpleService.getTopBannerList();
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
