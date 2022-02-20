package com.bezkoder.springjwt.publicAllowance.publicController.categoryController;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.publicAllowance.publicServices.CategoryImplePublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.BannerUrlMappingPublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.CategoryUrlMappingPublic;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFinalCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class CategoryControllerPublic {

    @Autowired
    private CategoryImplePublic categoryImplePublic;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductFinalCategoryRepository productFinalCategoryRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;



    @GetMapping(CategoryUrlMappingPublic.GET_FINAL_CATEGORY_LIST_PUBLIC)
    public ResponseEntity<?> getFinalCategoryListPublic(@PathVariable(required = true) String subCategoryName) throws InterruptedException {
        List<ProductFinalCateogoryForm>  data = this.categoryImplePublic.getFinalCategoryListPublic(subCategoryName);

        if(data != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }


    @GetMapping(CategoryUrlMappingPublic.GET_FINAL_CATEGORY_LIST_public)
    public ResponseEntity<?> getFinalCategoryList() throws InterruptedException {
        List<ProductFinalCateogoryForm>  data = this.categoryImplePublic.getFinalCategoryList();

        if(data != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }




}
