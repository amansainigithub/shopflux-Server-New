package com.bezkoder.springjwt.publicAllowance.publicController.productController;

import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.publicAllowance.publicServices.ProductImplePublic;
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
public class ProductControllerPublic {
    @Autowired
    private ProductImplePublic productImplePublic;

    @Autowired
    private BucketRepository bucketRepository;

    @GetMapping(ProductUrlMappingPublic.GET_PRODUCT_LIST_PUBLIC)
    public ResponseEntity<?> getProductList_public()
    {
        List<ProductForm> list = this.productImplePublic.getProductList();
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
