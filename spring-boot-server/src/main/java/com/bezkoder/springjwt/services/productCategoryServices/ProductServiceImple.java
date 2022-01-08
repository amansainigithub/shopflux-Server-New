package com.bezkoder.springjwt.services.productCategoryServices;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.bucket.model.BucketForm;
import com.bezkoder.springjwt.entities.productEntities.ProductFileUrls;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.helper.RandomNumber;
import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductCategory;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFileUrlsRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRootCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ProductServiceImple implements ProductCategory {

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

    @Override
    public ProductForm saveProduct(ProductForm productForm) {

       try {


           //VALIDATION
           if(!this.productValidation(productForm))
           {
               return null;
           }

           //SET SUB-PRODUCT-ID [HF]
           productForm.setSubCategoryId(String.valueOf(productForm.getProductSubCategoryForm().getProductSubCategoryId()));

           //SET ROOT-PRODUCT-ID [BY ID]
          ProductSubCategoryForm rootData = this.productSubCategoryRepository.
                                              findById(productForm.getProductSubCategoryForm().getProductSubCategoryId())
                                              .get();

          //SET ROOT CATEGORY ID TO PRODUCT-DATA
          productForm.setRootCategoryId(String.valueOf(rootData.getProductRootCategoryForm().getProductRootCategoryId()));

           //Get UNIQUE SKU NUMBER
        ProductForm skuProductForm = this.getUniqueSKU_Number(productForm);

           //Get UNIQUE USN NUMBER
        ProductForm usnProductForm = this.getUniqueProductUSN_Number(productForm);


        //SET CURRENT DATE AND TIME
          usnProductForm.setCreatingDate(this.getCurrentDateAndTime());

        //SET UPDATED DATE AND TIME
          usnProductForm.setLastUpdatedDate(this.getCurrentDateAndTime());


           return this.productRepository.save(usnProductForm);
       }
       catch (Exception e)
       {
           e.printStackTrace();
           return null;
       }
    }

    @Override
    public boolean deleteProduct(long productId) {
        boolean flag=false;
        try
        {
            this.productRepository.deleteById(productId);
            flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
            flag=false;
        }
        return flag;
    }

    @Override
    public List<ProductForm> getProductList() {
       return this.productRepository.findAll();
    }


    @Override
    public ProductForm getProductById(long productId) {
        ProductForm productForm = this.productRepository.findById(productId).get();
        if(productForm != null)
        {
            return productForm;
        }
        else
        {
            return null;
        }
    }



    public   List<ProductFileUrls> getProductFilesByProductId(String productId) {
       List<ProductFileUrls> productFileUrls = this.productFileUrlsRepository.findByproductId(productId);
;        if(productFileUrls.size() > 0)
        {
            return productFileUrls;
        }
        else
        {
            return null;
        }
    }



    public ProductForm getUniqueSKU_Number(ProductForm productForm)
    {
        boolean looping=true;
        try {
            while(looping)
            {
                int randomNumber = RandomNumber.getRandomNumber();
                ProductForm result = this.productRepository.findByproductSKU(String.valueOf(randomNumber));

                if(result!=null)
                {
                }
                else
                {
                    productForm.setProductSKU(String.valueOf(randomNumber));
                    looping=false;
                    break;
                }
            }
        }
        catch (Exception e)
        {
                e.printStackTrace();

        }
        return  productForm;
    }


    public ProductForm getUniqueProductUSN_Number(ProductForm productForm)
    {
        boolean looping=true;
        try {
            while(looping)
            {
                long randomNumber = RandomNumber.getRandomNumberLongType();
                ProductForm result = this.productRepository.findByproductUSN(String.valueOf(randomNumber));

                if(result!=null)
                {
                }
                else
                {
                    productForm.setProductUSN(String.valueOf(randomNumber).replace("-",""));
                    looping=false;
                    break;
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return  productForm;
    }


    public String  getCurrentDateAndTime()
    {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }



    @Override
    public boolean setProductThumbnail(String productId, String bucketId) {
    ProductForm productForm   =    this.productRepository.findById(Long.parseLong(productId)).get();
    BucketForm bucketForm  =    this.bucketRepository.findById(Long.parseLong(bucketId)).get();

        productForm.setThumbnailUrl(bucketForm.getFileUrl());

    if(this.productRepository.save(productForm) != null)
        return true;
       return false;
    }



    public boolean productValidation(ProductForm productForm)
    {
        boolean flag=true;

        //getProductName
       ProductForm product_1 = this.productRepository.findByproductName(productForm.getProductName());
       if(product_1 != null)
       {
           return false;
       }

       if(productForm.getProductName().isEmpty() && productForm.getManufacturingPrice().isEmpty() && productForm.getMrpPrice().isEmpty() &&
          productForm.getSellPrice().isEmpty() && productForm.getProductWeight().isEmpty() && productForm.getQuantity().isEmpty()
                )
       {
           return false;
       }
        return flag;
    }



}
