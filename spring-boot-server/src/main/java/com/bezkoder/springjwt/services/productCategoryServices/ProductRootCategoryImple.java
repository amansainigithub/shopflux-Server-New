package com.bezkoder.springjwt.services.productCategoryServices;

import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductRootCategory;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRootCategoryRepository;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductRootCategoryImple implements ProductRootCategory {

    @Autowired
    private ProductRootCategoryRepository productRootCategoryRepository;

    @Override
    public ProductRootCategoryForm saveProductRootCategory(ProductRootCategoryForm productRootCategoryForm) {

        ProductRootCategoryForm productRootCategory =null;
            try {
                productRootCategory= this.productRootCategoryRepository.save(productRootCategoryForm);
            }
             catch (Exception e)
            {
                e.printStackTrace();
            }

            return productRootCategory;

    }

    @Override
    public List<ProductRootCategoryForm> getRootCategoryList() {
       return this.productRootCategoryRepository.findAll();
    }

    @Override
    public boolean removeRootCategory(long rootCategoryid) {
        boolean flag=false;
        try{
            this.productRootCategoryRepository.deleteById(rootCategoryid);
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public ProductRootCategoryForm getRootCategoryById(long rootCategoryid) {

        try {
          ProductRootCategoryForm productRootCategoryForm=   this.productRootCategoryRepository.findById(rootCategoryid).get();
          if(productRootCategoryForm !=null)
          {
                return productRootCategoryForm;
          }
          else
          {
              throw new NullPointerException("Data Not Find Exception");
          }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            e.getMessage();
            return null;
        }


    }

    @Override
    public boolean updateRootCategory(ProductRootCategoryForm productRootCategoryForm) {
        boolean flag=false;
       try {

           if(this.productRootCategoryRepository.save(productRootCategoryForm) != null)
                flag=true;

       }
       catch (Exception e)
       {
           e.printStackTrace();
           flag =false;
       }
       return flag;
    }


}
