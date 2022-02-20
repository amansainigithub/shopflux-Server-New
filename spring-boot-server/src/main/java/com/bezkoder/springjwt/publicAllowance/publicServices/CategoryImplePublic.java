package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.CategoryInterfacePublic;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFinalCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRootCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CategoryImplePublic implements CategoryInterfacePublic {

    @Autowired
    private ProductRootCategoryRepository productRootCategoryRepository;

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductFinalCategoryRepository productFinalCategoryRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;


    @Override
    public List<ProductFinalCateogoryForm>  getFinalCategoryListPublic(String subCategoryName) {

        List<ProductFinalCateogoryForm> data =   this.productFinalCategoryRepository.findAll();

        List<ProductFinalCateogoryForm> filterData= new ArrayList<>();
        for(ProductFinalCateogoryForm finalCategoryData : data)
        {
            if(finalCategoryData.getSubCateName().equals("TOPWEAR"))
            {
                System.out.println(finalCategoryData.getSubCateName());
                System.out.println("**************");
                filterData.add(finalCategoryData);
            }

        }
        return  filterData;
    }

    public List<ProductFinalCateogoryForm> getFinalCategoryList() {
        List<ProductFinalCateogoryForm> finalCategoryList = null;
        try {
               finalCategoryList = this.productFinalCategoryRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return finalCategoryList;
    }
}
