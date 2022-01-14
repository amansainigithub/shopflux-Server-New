package com.bezkoder.springjwt.bucket.helper;

import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.bucket.model.BucketFileLinkingForm;
import com.bezkoder.springjwt.bucket.model.BucketForm;
import com.bezkoder.springjwt.entities.productEntities.ProductFileUrls;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductFileUrlsRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRootCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class BucketHelper {


    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private BucketHelper bucketHelper;

    @Autowired
    private ProductFileUrlsRepository productFileUrlsRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductRootCategoryRepository productRootCategoryRepository;

    @Autowired
    private ProductSubCategoryRepository productSubCategoryRepository;

    public String getDate()
    {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return  formatter.format(date);
    }
    public String getTime()
    {
        Clock c = Clock.systemUTC();
        return c.instant().toString();
    }

    public Path directoryCheckerOrMaker(String directory)
    {
        Path path = Paths.get(directory);
        File dir = new File(path.toString());
        dir.mkdir();

        return path;
    }




    public String getAndCreateAbsolutePath()
    {
        try {
           return  new ClassPathResource("/static").getFile().getAbsolutePath();
        }
        catch (Exception e)
        {
            return  null;
        }
    }

    public boolean deleteOldImage(String path,String deleteImageName)
    {

        boolean flag=false;

        try {
            //getting the Real Path of the Directory
            Path filePath = Paths.get(path);
            File file=new File(filePath+ File.separator+deleteImageName);
            flag=file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }


    //ROOT URLS UPDATED
    public boolean updatedRootFilesUrls(BucketFileLinkingForm bucketFileLinkingForm)
    {
        BucketForm bucketForm=null;
        boolean flag=false;
        try{
            ProductRootCategoryForm productRootCategoryData =
                    this.productRootCategoryRepository.findById(Long.parseLong(bucketFileLinkingForm.getCategoryId())).get();


            bucketForm =  this.bucketRepository.findById(Long.parseLong(bucketFileLinkingForm.getBucketId())).get();

            if(productRootCategoryData == null || bucketForm == null)
            {
                throw new NullPointerException("Data Not found Here !!!");
            }

            if(bucketFileLinkingForm.getFileTemplateName().equals("IMAGE"))
            {
                productRootCategoryData.setImageUrl(bucketForm.getFileUrl());
                this.productRootCategoryRepository.save(productRootCategoryData);
                flag=true;

            }
            else if (bucketFileLinkingForm.getFileTemplateName().equals("VIDEO"))
            {
                productRootCategoryData.setVideoUrl(bucketForm.getFileUrl());
                this.productRootCategoryRepository.save(productRootCategoryData);
                flag=true;
            }

            bucketForm.setIsLinking("YES");
            this.bucketRepository.save(bucketForm);
        }catch (Exception e)
        {
            e.printStackTrace();
            flag=false;
        }
        return flag;
    }

    //SUB URLS UPDATED
    public boolean updatedSubFilesUrls(BucketFileLinkingForm bucketFileLinkingForm)
    {
        BucketForm bucketForm=null;
        boolean flag=false;
        try{
            ProductSubCategoryForm productSubCategoryForm =
                    this.productSubCategoryRepository.findById(Long.parseLong(bucketFileLinkingForm.getCategoryId())).get();


            bucketForm =  this.bucketRepository.findById(Long.parseLong(bucketFileLinkingForm.getBucketId())).get();

            if(productSubCategoryForm == null || bucketForm == null)
            {
                throw new NullPointerException("Data Not found Here !!!");
            }

            if(bucketFileLinkingForm.getFileTemplateName().equals("IMAGE"))
            {
                productSubCategoryForm.setImageUrl(bucketForm.getFileUrl());
                this.productSubCategoryRepository.save(productSubCategoryForm);
                flag=true;

            }
            else if (bucketFileLinkingForm.getFileTemplateName().equals("VIDEO"))
            {
                productSubCategoryForm.setVideoUrl(bucketForm.getFileUrl());
                this.productSubCategoryRepository.save(productSubCategoryForm);
                flag=true;
            }
            bucketForm.setIsLinking("YES");
            this.bucketRepository.save(bucketForm);
        }catch (Exception e)
        {
            e.printStackTrace();
            flag=false;
        }
        return flag;
    }



    //PRODUCT URLS ADDED
    public boolean addProductUrls(BucketFileLinkingForm bucketFileLinkingForm)
    {
//        System.out.println("Product starting 999");
        BucketForm bucketForm=null;
        boolean flag=false;
        try{
            ProductForm productForm =
                    this.productRepository.findById(Long.parseLong(bucketFileLinkingForm.getCategoryId())).get();


            bucketForm =  this.bucketRepository.findById(Long.parseLong(bucketFileLinkingForm.getBucketId())).get();

            if(productForm == null || bucketForm == null)
            {
                throw new NullPointerException("Data Not found Here !!!");
            }

            if(bucketFileLinkingForm.getFileTemplateName().equals("IMAGE") || bucketFileLinkingForm.getFileTemplateName().equals("VIDEO"))
            {
                ProductFileUrls productFileUrls=new ProductFileUrls();
                productFileUrls.setFileUrl(bucketForm.getFileUrl());
                productFileUrls.setProductId(String.valueOf(productForm.getProductId()));
                productFileUrls.setBucketId(bucketForm.getBucketId());
                productFileUrls.setProductForm(productForm);
                this.productFileUrlsRepository.save(productFileUrls);

                if(this.isBucketLinking(bucketFileLinkingForm))
                {
                    flag=true;
                }
                else{
                    flag=false;
                }


            }

        }catch (Exception e)
        {
            e.printStackTrace();
            flag=false;
        }
        return flag;
    }


    //If product or client Linking
    // the image by bucket Id or File Url or
    // that update the bucket-Hub value yes or no @column name isLinking
     public boolean isBucketLinking(BucketFileLinkingForm bucketFileLinkingForm )
     {
          BucketForm bucketForm  = this.bucketRepository.findById(Long.parseLong(bucketFileLinkingForm.getBucketId())).get();
          bucketForm.setIsLinking("YES");

         BucketForm updatedBucket =   this.bucketRepository.save(bucketForm);

        if(updatedBucket != null)
            return true;
            return false;
         }


//
//         public boolean deleteFile(String directory,String fileName)
//         {
//             boolean flag=false;
//             try {
//                 Path path = Paths.get(directory);
//                 File dir = new File(path.toString());
//                 dir.delete();
//             }
//             catch (Exception e)
//             {
//                 e.printStackTrace();
//             }
//
//         }
}
