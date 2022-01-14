package com.bezkoder.springjwt.services.bannerService;

import com.bezkoder.springjwt.bucket.BucketUrl;
import com.bezkoder.springjwt.bucket.helper.BucketHelper;
import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.interfaces.bannerInterface.Banner;
import com.bezkoder.springjwt.repositories.bannerRepo.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class BannerImple implements Banner {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BucketHelper bucketHelper;

    @Override
    public BannerForm createBanner(BannerForm bannerForm) {
        try {
              BannerForm result =  this.bannerRepository.save(bannerForm);
              if(result != null)
              {
                  return result;
              }
              else
              {
                  throw  new NullPointerException("Data Not saved in Banner module");
              }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean uploadBannerFileByBannerId(String bannerId, MultipartFile file) {
           boolean flag=false;
        try {
            BannerForm bannerForm =   this.bannerRepository.findById(Long.parseLong(bannerId.trim())).get();

            if(bannerForm == null)
            {
                throw new RuntimeException("Could Not find Data to this id ==> " +bannerId);
            }
            String folderName="BANNER";
            Path path   =   this.bucketHelper.directoryCheckerOrMaker(folderName.trim());

            BannerForm updatedResult =  this.updateBannerFile(bannerForm,file,path,folderName.trim());

            if(updatedResult != null)
                flag=true;
            else
                 throw new RuntimeException("Something went wrong");
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return flag;
    }


    private BannerForm updateBannerFile(BannerForm bannerForm,MultipartFile file,Path path,String folderName)
    {
       try {
           String finalName="BANNER-TEMP-"+bannerForm.getBannerId()+"-"+file.getOriginalFilename().replace(" ","");

           Files.copy(file.getInputStream(), path.resolve(finalName));

           //Final URL
           bannerForm.setFolderName(folderName);
           bannerForm.setFilename(finalName);
           bannerForm.setFileSize(String.valueOf(file.getSize()));
           bannerForm.setFileContentType(file.getContentType());
           bannerForm.setFileUrl(BucketUrl.IMAGE_ACCESS_URL+BucketUrl.BANNER_ACCESS_URI+"BANNER/"+finalName);

           return  this.bannerRepository.save(bannerForm);

       }
       catch (Exception e)
       {
           e.printStackTrace();
       }

       return null;

    }



    public BannerForm getBannerById(String bannerId)
    {
            try {
             return  this.bannerRepository.findById(Long.parseLong(bannerId)).get();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
    }

    public List<BannerForm> getBannerList()
    {
        try {
            return this.bannerRepository.findAll();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean deleteBannerById(String bannerId) {
        boolean flag=false;
        try {
         BannerForm bannerForm = this.bannerRepository.findById(Long.parseLong(bannerId)).get();

         if(bannerForm != null)
         {
             this.bucketHelper.deleteOldImage(bannerForm.getFolderName(),bannerForm.getFilename());
             this.bannerRepository.deleteById(Long.parseLong(bannerId));
            flag=true;
         }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }


}
