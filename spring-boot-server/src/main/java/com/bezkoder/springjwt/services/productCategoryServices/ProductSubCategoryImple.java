package com.bezkoder.springjwt.services.productCategoryServices;

import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductSubCategory;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRootCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSubCategoryImple  implements ProductSubCategory {

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;
    @Autowired
    private ProductRootCategoryRepository productRootCategoryRepository;

    @Override
    public ProductSubCategoryForm addProductSubCategory(ProductSubCategoryForm productSubCategoryForm) {
        ProductSubCategoryForm productSubCategory =null;
        try {
         long id=  productSubCategoryForm.getProductRootCategoryForm().getProductRootCategoryId();

         ProductRootCategoryForm productRootCategoryForm= this.productRootCategoryRepository.findById(id).get();
         productSubCategoryForm.setProductRootCategoryForm(productRootCategoryForm);

         ProductSubCategoryForm productSubCategor= this.productSubCategoryRepository.save(productSubCategoryForm);
         return productSubCategor;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productSubCategory;
    }

    @Override
    public List<ProductSubCategoryForm> getProductSubCategoryList() {
        return this.productSubCategoryRepository.findAll();
    }

    @Override
    public boolean removeSubCategory(long subCategoryId) {
        boolean flag=false;
        try{
            this.productSubCategoryRepository.deleteById(subCategoryId);
             flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            flag= false;
        }
        return flag;
    }

    @Override
    public ProductSubCategoryForm getSubCategoryById(long subCategoryId) {

            ProductSubCategoryForm productSubCategoryForm=   this.productSubCategoryRepository.findByproductSubCategoryId(subCategoryId);
           return productSubCategoryForm;
    }


    public ProductSubCategoryForm updateSubCategory(ProductSubCategoryForm productSubCategoryForm) {
        ProductSubCategoryForm productSubCategory =null;
        try {
            System.out.println(productSubCategoryForm.toString());
            long id=  productSubCategoryForm.getProductRootCategoryForm().getProductRootCategoryId();
            System.out.println(id);

            ProductRootCategoryForm productRootCategoryForm= this.productRootCategoryRepository.findById(id).get();
            productSubCategoryForm.setProductRootCategoryForm(productRootCategoryForm);

            ProductSubCategoryForm productSubCategor= this.productSubCategoryRepository.save(productSubCategoryForm);
            return productSubCategor;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productSubCategory;
    }

}
