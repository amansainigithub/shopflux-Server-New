package com.bezkoder.springjwt.services.productCategoryServices;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.bucket.model.BucketForm;
import com.bezkoder.springjwt.entities.productEntities.*;
import com.bezkoder.springjwt.helper.RandomNumber;
import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductCategory;
import com.bezkoder.springjwt.repositories.productCategoryRepository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    @Autowired
    private ProductFinalCategoryRepository productFinalCategoryRepository;

    @Override
    public ProductForm saveProduct(ProductForm productForm) {

       try {

         //  System.out.println(productForm.toString());

           //VALIDATION
           if(!this.productValidation(productForm))
           {
               return null;
           }

//           //SET FINAL CATEGORY ID
           productForm.setFinalCategoryId(String.valueOf(productForm.getProductFinalCategoryForm().getProductFinalCategoryId()));



           //GET[FINAL-CATEGORY] AND SET SUB CATEGORY DATA
          ProductFinalCateogoryForm  productFinalCategoryData = this.productFinalCategoryRepository.
                                                                  findById(Long.parseLong(productForm.getFinalCategoryId()))
                                                                  .get();

          //SET SUB CATEGORY ID TO PRODUCT-DATA
          productForm.setSubCategoryId(String.valueOf(productFinalCategoryData.getProductSubCategoryForm().getProductSubCategoryId()));

          productForm.setRootCategoryId(String.valueOf(productFinalCategoryData.getProductSubCategoryForm()
                                        .getProductRootCategoryForm()
                                        .getProductRootCategoryId()));

           //Get UNIQUE SKU NUMBER
        ProductForm skuProductForm = this.getUniqueSKU_Number(productForm);

           //Get UNIQUE USN NUMBER
        ProductForm usnProductForm = this.getUniqueProductUSN_Number(productForm);


        //SET CURRENT DATE AND TIME
          usnProductForm.setCreatingDate(this.getCurrentDateAndTime());

        //SET UPDATED DATE AND TIME
          usnProductForm.setLastUpdatedDate(this.getCurrentDateAndTime());

          //CALCULATE OFF PRICE AND SET TO PRODUCT Obj...
            this.offPrice(productForm.getMrpPrice(),productForm.getSellPrice(),productForm);

           return this.productRepository.save(usnProductForm);
       }
       catch (Exception e)
       {
           e.printStackTrace();
           return null;
       }
    }

    public void offPrice(String mrpPrice,String sellPrice ,ProductForm productForm)
    {
        try {
            int result = 0;
            result = ((Integer.parseInt(sellPrice) - Integer.parseInt(mrpPrice)) * 100) / Integer.parseInt(mrpPrice);
            productForm.setSavePrice(String.valueOf(result));
        }
        catch (Exception e)
        {
            e.printStackTrace();
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
       List<ProductForm> list =  this.productRepository.findAll();

        ArrayList<ProductForm> ishu=new ArrayList<>();

       for(ProductForm productForm : list)
       {


          List<ProductFileUrls> li =  productForm.getProductFileUrls();

          for(ProductFileUrls productFileUrls : li)
          {
              productFileUrls.getFileUrl();
              System.out.println("*************************************************");
              System.out.println(productFileUrls.getFileUrl());

          }

          productForm.setProductFileUrls(li);
          ishu.add(productForm);
       }

       return ishu;
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
        boolean flag=false;

    ProductForm productForm   =    this.productRepository.findById(Long.parseLong(productId)).get();
    BucketForm bucketForm  =    this.bucketRepository.findById(Long.parseLong(bucketId)).get();

   try {
           if(bucketForm.getImageType().equals("video/mp4"))
           {
               productForm.setVideoThumbnailUrl(bucketForm.getFileUrl());
               if(this.productRepository.save(productForm) != null)
                   flag=true;
           }
           else if(bucketForm.getImageType().equals("image/jpeg"))
           {
               List<ProductFileUrls > productFileUrls = productForm.getProductFileUrls();

               for(ProductFileUrls filesData : productFileUrls)
               {
                   if(String.valueOf(filesData.getBucketId()).equals(bucketId.trim()))
                   {
                       filesData.setImageThumbnail("YES");
                       this.productFileUrlsRepository.save(filesData);
                       flag=true;
                   }
                   else
                   {
                       filesData.setImageThumbnail("NO");
                       this.productFileUrlsRepository.save(filesData);
                       flag=true;
                   }
               }

//               productForm. setThumbnailUrl(bucketForm.getFileUrl());
//               if(this.productRepository.save(productForm) != null)
//                   flag=true;
           }
       }
       catch (Exception e)
       {
            e.printStackTrace();
       }
       return flag;
    }

    @Override
    public boolean setThumbnailMainPage(String productId, String bucketId) {
        boolean flag=false;

        ProductForm productForm   =    this.productRepository.findById(Long.parseLong(productId)).get();
        BucketForm bucketForm  =    this.bucketRepository.findById(Long.parseLong(bucketId)).get();

        try {
            if(bucketForm.getImageType().equals("video/mp4"))
            {
                productForm.setVideoThumbnailUrl(bucketForm.getFileUrl());
                if(this.productRepository.save(productForm) != null)
                    flag=true;
            }
            else if(bucketForm.getImageType().equals("image/jpeg"))
            {
               productForm. setThumbnailUrl(bucketForm.getFileUrl());
               if(this.productRepository.save(productForm) != null)
                   flag=true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
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
