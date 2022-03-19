package com.bezkoder.springjwt.services.productCategoryServices;

import com.bezkoder.springjwt.entities.productEntities.ProductSizeCreateForm;
import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductSizeCreateInter;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSizeCreateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeCreateServiceImple implements ProductSizeCreateInter {

    @Autowired
    private ProductSizeCreateRepo productSizeCreateRepo;

    @Override
    public ProductSizeCreateForm createProductSize(ProductSizeCreateForm productSizeCreateForm) {
      ProductSizeCreateForm productSize = null;
       try {
            productSize = this.productSizeCreateRepo.save(productSizeCreateForm);
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return productSize;
    }

    public boolean deleteProductSize(String id) {
        boolean flag=false;
        try {
                this.productSizeCreateRepo.deleteById(Long.parseLong(id.trim()));
                    flag =true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ProductSizeCreateForm getProductDetailsSizeByid(String sizeId) {
        try {
          return   this.productSizeCreateRepo.findById(Long.parseLong(sizeId.trim())).get();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<ProductSizeCreateForm> getProductSizeList() {
        List<ProductSizeCreateForm> list = null;
       try {
           list = this.productSizeCreateRepo.findAll();
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
       return list;
    }
}
