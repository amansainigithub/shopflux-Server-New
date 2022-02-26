package com.bezkoder.springjwt.publicAllowance.publicController.productController;

import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.publicAllowance.publicServices.ProductImplePublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.ProductUrlMappingPublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFinalCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class ProductControllerPublic {
    @Autowired
    private ProductImplePublic productImplePublic;

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private ProductRepository productRepository;


    @GetMapping(ProductUrlMappingPublic.GET_PRODUCT_LIST_PUBLIC)
    public ResponseEntity<?> getProductList_public() throws InterruptedException {

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

    @GetMapping(ProductUrlMappingPublic.GET_PRODUCT_BY_FINAL_CATEGORY)
    public ResponseEntity<?> getProductByFinalCategory() throws InterruptedException {

        List<ProductForm> list = this.productImplePublic.getProductByFinalCategory();
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(ProductUrlMappingPublic.GET_PRODUCT_BY_FINAL_CATEGORY_NAME_PUBLIC)
    public ResponseEntity<?> getProductListByFinalCategoryName(@PathVariable(required = true)String finalCategoryName ) throws InterruptedException {

        List<ProductForm> list = this.productImplePublic.getProductListByFinalCategoryName(finalCategoryName);
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(ProductUrlMappingPublic.GET_PRODUCT_BY_PRODUCT_ID)
    public ResponseEntity<ProductForm> getProductByProductId(@PathVariable(required = true)String productId ) throws InterruptedException {

         ProductForm productData = this.productImplePublic.getProductByProductId(productId);
        if(productData != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(productData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }



}
