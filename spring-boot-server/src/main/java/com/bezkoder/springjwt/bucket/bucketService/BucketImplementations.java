package com.bezkoder.springjwt.bucket.bucketService;

import com.bezkoder.springjwt.bucket.BucketUrl;
import com.bezkoder.springjwt.bucket.bucketInterfaces.Bucket;
import com.bezkoder.springjwt.bucket.bucketRepository.BucketRepository;
import com.bezkoder.springjwt.bucket.helper.BucketHelper;
import com.bezkoder.springjwt.bucket.model.BucketFileLinkingForm;
import com.bezkoder.springjwt.bucket.model.BucketForm;
import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductRootCategoryRepository;
import com.bezkoder.springjwt.repositories.productCategoryRepository.ProductSubCategoryRepository;
import com.bezkoder.springjwt.services.productCategoryServices.ProductServiceImple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class BucketImplementations implements Bucket {

    @Autowired
    private BucketRepository bucketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BucketHelper bucketHelper;

    @Autowired
    private ProductRootCategoryRepository productRootCategoryRepository;

    @Autowired
            private ProductSubCategoryRepository productSubCategoryRepository;

    List<BucketForm> list=new ArrayList<>();

    @Override
    public BucketForm saveFilesInBucket(BucketForm bucketForm) {
        return this.bucketRepository.save(bucketForm);
    }



    public List<?> uploadService(List<MultipartFile> list,String pcName,String pcId)
    {
       Path path =  this.bucketHelper.directoryCheckerOrMaker(pcName);

          //IF DATA IS [ROOT]
          if(pcName.equals(BucketUrl.ROOT))
          {
                 this.uploadImages(list, "folderPath", pcName, pcId,path);
                 return this.list;
          }

        if(pcName.equals(BucketUrl.SUB))
        {
            this.uploadImages(list, "folderPath", pcName, pcId,path);
            return this.list;
        }

        if(pcName.equals(BucketUrl.PRODUCT))
        {
            this.uploadImages(list, "folderPath", pcName, pcId,path);
            return this.list;
        }

        return null;
    }








    public void uploadImages(List<MultipartFile> files,String folderPath,String pcName,String pcId,Path path)
    {
       try
       {
           for(MultipartFile multipartFile : files)
           {
               //Writing the Image
               long currentTime= System.currentTimeMillis();
               String currentTimeAndImagename="BUCKET-HUB-SN-001-"+currentTime+"-"+multipartFile.getSize()
                                                +"-"+multipartFile.getOriginalFilename().replace(" ","");

                   try {
                       Files.copy(multipartFile.getInputStream(), path.resolve(currentTimeAndImagename));
                   } catch (Exception e) {
                       throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
                   }

                   //Final URL
                     Resource resource   =   this.load(currentTimeAndImagename ,path);

               this.saveImageData(BucketUrl.IMAGE_ACCESS_URL+pcName,
                       BucketUrl.IMAGE_ACCESS_URL+BucketUrl.API_ACCESS_URI+pcName+"/"+currentTimeAndImagename,
                                    currentTimeAndImagename,
                                    multipartFile.getSize(),
                                    pcName,
                                    pcId,
                                    multipartFile.getContentType());

//               fileOutputStream.close();
           }
       }catch (Exception e)
       {
            e.printStackTrace();
       }
    }

    public void saveImageData(String folderPath,String finalPath,String imageName,long imageSize,String pcName,String pcId,String contentType)
    {
            String imgSize = String.valueOf(imageSize);
            BucketForm bucketForm=new BucketForm();
            bucketForm.setImageSize(imgSize);
            bucketForm.setImageName(imageName);
            bucketForm.setFileUrl(finalPath);
            bucketForm.setFolderPath(folderPath);
            bucketForm.setPcName(pcName);
            bucketForm.setFolderName(pcName);
            bucketForm.setPcId(pcId);
            bucketForm.setImageType(contentType);
            bucketForm.setCreatedDate(this.bucketHelper.getDate());
            bucketForm.setCreatedTime(this.bucketHelper.getTime());
            BucketForm bucketData= this.saveFilesInBucket(bucketForm);
            this.list.add(bucketData);

    }


    public Resource load(String filename,Path path) {
        try {
            Path file = path.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


    public Resource loadWithImagePath(String folderName,String filename) {
        try {
            Path root = Paths.get(folderName.trim());
            Path file = root.resolve(filename.trim());
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }




    @Override
    public List<BucketForm> getFilesById(String pcId) {
       return this.bucketRepository.findBypcId(pcId);
    }

    @Override
    public boolean deleteByBucketId(String bucketId) {
        boolean flag=false;
      try {
          BucketForm bucketForm =  this.bucketRepository.findById(Long.parseLong(bucketId)).get();
          this.bucketHelper.deleteOldImage(bucketForm.getFolderName(),bucketForm.getImageName());
          this.bucketRepository.deleteById(Long.parseLong(bucketId));
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
    public boolean bindFile(BucketFileLinkingForm bucketFileLinkingForm) {
        BucketForm bucketForm=null;
        boolean flag=false;

        try {
            //FOR ROOT
            if(bucketFileLinkingForm.getCategoryHierarchyName().equals("ROOT"))
            {
               if(this.bucketHelper.updatedRootFilesUrls(bucketFileLinkingForm))
                   flag=true;
               else
                   flag=false;
            }

            //FOR SUB
           else if(bucketFileLinkingForm.getCategoryHierarchyName().equals("SUB"))
            {
                if(this.bucketHelper.updatedSubFilesUrls(bucketFileLinkingForm))
                    flag=true;
                else
                    flag=false;
            }

            //FOR PRODUCT
            else if(bucketFileLinkingForm.getCategoryHierarchyName().equals("PRODUCT"))
            {
                if(!this.bucketHelper.isLinkingChecker(bucketFileLinkingForm)) {
                    if (this.bucketHelper.addProductUrls(bucketFileLinkingForm))
                        flag = true;
                    else
                        flag = false;
                }
                else
                {
                    throw  new RuntimeException("File Already Linking");
                }
            }
            else
            {
                flag=false;
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            flag=false;

        }
       return flag;
    }



    //REMOVE FILE LINKING
    @Override
    public boolean removeFileLinking(BucketFileLinkingForm bucketFileLinkingForm) {
        boolean flag=false;

        try {
            //FOR PRODUCT
             if(bucketFileLinkingForm.getCategoryHierarchyName().equals("PRODUCT"))
            {
                if(this.bucketHelper.removeLink(bucketFileLinkingForm))
                    flag=true;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        return flag;
    }









    /**
    public void updateRootCategoryImageUrl(String pcId ,String imageUrl)
    {
        ProductRootCategoryForm productRootCategoryForm = this.productRootCategoryRepository.findById(Long.parseLong(pcId));
        productRootCategoryForm.setImageUrl(imageUrl);
        this.productRootCategoryRepository.save(productRootCategoryForm);
    }

    public void updateSubCategoryImageUrl(String pcId ,String imageUrl)
    {
        ProductSubCategoryForm productSubCategoryForm = this.productSubCategoryRepository.findById(Long.parseLong(pcId)).get();
        productSubCategoryForm.setImageUrl(imageUrl);
        this.productSubCategoryRepository.save(productSubCategoryForm);
    }


    public void updateImage(List<MultipartFile> files,BucketForm bucketForm)
    {
        try
        {
            if(this.bucketHelper.deleteOldImage(bucketForm.getFolderPath(),bucketForm.getImageName())== true)
            {
                System.out.println("deleted old image");

                for(MultipartFile multipartFile : files) {
                    //Writing the Image
                    //Writing the Image
                    byte[] bytes =multipartFile.getBytes();
                    long currentTime= System.currentTimeMillis();
                    String currentTimeAndImagename="-"+currentTime+"-"+multipartFile.getOriginalFilename();
                    String finalPath=bucketForm.getFolderPath()+File.separator+currentTimeAndImagename;
                    FileOutputStream fileOutputStream=new FileOutputStream(finalPath);
                    fileOutputStream.write(bytes);

                    fileOutputStream.close();

                    bucketForm.setImageUrl(finalPath);
                    bucketForm.setImageName(currentTimeAndImagename);
                    bucketForm.setImageSize(String.valueOf(multipartFile.getSize()));
                    bucketForm.setCreatedTime(this.bucketHelper.getTime());
                    bucketForm.setCreatedDate(this.bucketHelper.getDate());
                    list.add(this.saveFilesInBucket(bucketForm));

                    if(bucketForm.getPcName().equals("ROOT")) {
                        this.updateRootCategoryImageUrl(bucketForm.getPcId().trim(), finalPath);
                    }

                    else if(bucketForm.getPcName().equals("SUB"))
                    {
                        this.updateSubCategoryImageUrl(bucketForm.getPcId().trim(), finalPath);
                    }

                    else if(bucketForm.getPcName().equals("PRODUCT"))
                    {

                    }

                }
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
     **/

}
