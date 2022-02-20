package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.entities.categoryProductSingleEntites.CStructureFinalCategory;
import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.ProductInterfacePublic;
import com.bezkoder.springjwt.repositories.productCategoryRepository.*;
import com.bezkoder.springjwt.repositories.productCategorySingleRepo.FinalCategorySingleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
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

    @Autowired
    private FinalCategorySingleRepository finalCategorySingleRepository;


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

    public List<ProductForm> getProductByFinalCategory() {

         List<CStructureFinalCategory> finalCategoryList =  this.finalCategorySingleRepository.findAll();


        List<ProductForm> productList  = this.productRepository.findByFinalCategoryId(finalCategoryList.get(0).getFinalCategoryId());

        return productList;
    }

    @Override

    public List<ProductForm> getProductListByFinalCategoryName(String finalCategoryName) {
        List<ProductForm> productList = null;
       try {

           ProductFinalCateogoryForm productFinalCateogoryForm = this.productFinalCategoryRepository.findByfinalCategoryName(finalCategoryName.trim());

           productList = this.productRepository.findByFinalCategoryId(String.valueOf(productFinalCateogoryForm.getProductFinalCategoryId()));
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return productList;
    }
}
