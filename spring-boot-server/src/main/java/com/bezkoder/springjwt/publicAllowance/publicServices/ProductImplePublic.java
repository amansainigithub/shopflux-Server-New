package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.ProductInterfacePublic;
import com.bezkoder.springjwt.repositories.productCategoryRepository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProductImplePublic implements ProductInterfacePublic {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRootCategoryRepository productRootCategoryRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    @Autowired
    private ProductFileUrlsRepository productFileUrlsRepository;

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private ProductFinalCategoryRepository productFinalCategoryRepository;


    @Override
    public List<ProductForm> getProductList() {
        List<ProductForm> productList=null;
      try {
          productList=this.productRepository.findAll();
      }
      catch (Exception e)
      {
          e.printStackTrace();
      }
      return productList;
    }
}
