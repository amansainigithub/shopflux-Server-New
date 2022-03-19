package com.bezkoder.springjwt.services.productCategoryServices;

import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSizeSetForProductForm;
import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductSizeSetForProductInterface;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSizeSetForProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductSizeSetForProductImple implements ProductSizeSetForProductInterface {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductSizeSetForProductRepo productSizeSetForProductRepo;

    @Override
    public ProductSizeSetForProductForm saveProductSizeSetProductForm(String productId, ProductSizeSetForProductForm productSizeSetForProductForm) {

        ProductSizeSetForProductForm productSizeSetForProduct =null;
        try {
            ProductForm productForm =  this.productRepository.findById(Long.parseLong(productId.trim())).get();

           productSizeSetForProductForm.setProductForm(productForm);

            productSizeSetForProduct =  this.productSizeSetForProductRepo.save(productSizeSetForProductForm);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return productSizeSetForProduct;

      }

    @Override
    public boolean removeProductSizeForProductById(String productSizeId) {
        boolean flag=false;
        try {

          this.productSizeSetForProductRepo.deleteById(Long.parseLong(productSizeId));
          flag=true;

        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public ProductSizeSetForProductForm getProductSizeForProductById(String productSizeId) {
        ProductSizeSetForProductForm productSizeSetForProductForm  = null ;
        try {
            productSizeSetForProductForm  =  this.productSizeSetForProductRepo.findById(Long.parseLong(productSizeId)).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return productSizeSetForProductForm;
    }

//    @Override
//    public List<ProductSizeSetForProductForm> getProductSizeListByProductId(String productId) {
//       ProductSizeSetForProductForm productSizeSetForProductForm = null;
//
//       try {
//            this.productSizeSetForProductRepo.find
//       }
//       catch (Exception e)
//       {
//           e.printStackTrace();
//       }
//    }
}
