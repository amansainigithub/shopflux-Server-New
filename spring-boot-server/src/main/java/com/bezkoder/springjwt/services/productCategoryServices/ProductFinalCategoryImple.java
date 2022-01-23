package com.bezkoder.springjwt.services.productCategoryServices;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductFinalCategory;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFinalCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductFinalCategoryImple implements ProductFinalCategory {

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    @Autowired
    private ProductFinalCategoryRepository productFinalCategoryRepository;

    @Override
    public ProductFinalCateogoryForm saveFinalProductCategory(ProductFinalCateogoryForm productFinalCateogoryForm) {
        ProductSubCategoryForm productSubCategory =null;
        try {
            long id=  productFinalCateogoryForm.getProductSubCategoryForm().getProductSubCategoryId();

            ProductSubCategoryForm productSubCategoryForm= this.productSubCategoryRepository.findById(id).get();
            ArrayList<ProductFinalCateogoryForm> list = new ArrayList<>();
            list.add(productFinalCateogoryForm);
            productSubCategoryForm.setProductFinalCateogoryForms(list);

            ProductSubCategoryForm productSubCateg= this.productSubCategoryRepository.save(productSubCategoryForm);
            return productFinalCateogoryForm;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductFinalCateogoryForm> getFinalCategoryList() {
       return this.productFinalCategoryRepository.findAll();
    }


    public ProductFinalCateogoryForm updateFinalCategory(ProductFinalCateogoryForm productFinalCateogoryForm) {
        ProductSubCategoryForm productSubCategory =null;
        try {
            System.out.println(productFinalCateogoryForm.toString());
            long id=  productFinalCateogoryForm.getProductSubCategoryForm().getProductSubCategoryId();
            System.out.println(id);

            ProductSubCategoryForm productSubCategoryForm= this.productSubCategoryRepository.findById(id).get();
            ArrayList<ProductFinalCateogoryForm> list = new ArrayList<>();
            list.add(productFinalCateogoryForm);
            productSubCategoryForm.setProductFinalCateogoryForms(list);

            ProductSubCategoryForm data= this.productSubCategoryRepository.save(productSubCategoryForm);
            return productFinalCateogoryForm;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ProductFinalCateogoryForm getFinalCategoryById(long productFinalCategoryId) {
      return  this.productFinalCategoryRepository.findById(productFinalCategoryId).get();
    }

}
