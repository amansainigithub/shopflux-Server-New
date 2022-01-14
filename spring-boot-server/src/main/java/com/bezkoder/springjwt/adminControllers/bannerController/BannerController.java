package com.bezkoder.springjwt.adminControllers.bannerController;

import com.bezkoder.springjwt.bucket.bucketService.BucketImplementations;
import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.interfaces.bannerInterface.Banner;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.services.bannerService.BannerImple;
import com.bezkoder.springjwt.urlMappings.BannerUrlMapping;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class BannerController {

    @Autowired
    private BannerImple bannerImple;

    @Autowired
    private BucketImplementations bucketImplementations;


    @PostMapping(BannerUrlMapping.CREATE_BANNER)
    public ResponseEntity<?> createBanner(@Valid @RequestBody BannerForm bannerForm) throws InterruptedException {
        Thread.sleep(5000);
           BannerForm bannerData =  this.bannerImple.createBanner(bannerForm);

           if(bannerData != null )
           {
               return  ResponseEntity.ok(new MessageResponse("Banner Successfully Created !!!"));
           }
           else
           {
                  return  ResponseEntity.ok(new MessageResponse("Banner Not Created !!! "));
           }

    }

    @PostMapping(BannerUrlMapping.UPLOAD_BANNER_FILE_BY_BANNER_ID)
    public ResponseEntity<?> uploadBannerFileByBannerId(@Valid @PathVariable("bannerId") String bannerId, MultipartFile file)
    {

        if(this.bannerImple.uploadBannerFileByBannerId(bannerId,file))
        {
            return ResponseEntity.ok(new MessageResponse("message : Data Successfully updated"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse("Data Not Updated !!!"));
        }

    }


    @GetMapping("/banner/{folderName}/{fileName:.+}")
    public ResponseEntity<Resource> getBannerFile(
            @PathVariable("folderName") String folderName,
            @PathVariable("fileName") String fileName
    ) {
        Resource file = bucketImplementations.loadWithImagePath(folderName,fileName);
        System.out.println("File Name");
        System.out.println(file.getFilename());
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }


    @GetMapping(BannerUrlMapping.GET_BANNER_BY_ID)
    public ResponseEntity<?> getBannerById(@PathVariable("bannerId") String bannerId)
    {
            BannerForm bannerForm = this.bannerImple.getBannerById(bannerId);
            if(this.bannerImple.getBannerById(bannerId) != null)
            {
                return  ResponseEntity.ok(bannerForm);
            }
            else
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Data Not Found Here !!!"));
            }
    }

    @GetMapping(BannerUrlMapping.GET_BANNER_LIST)
    public ResponseEntity<?> getBannerList()
    {
        List<BannerForm> bannerList = this.bannerImple.getBannerList();
        if(!bannerList.isEmpty())
        {
            return  ResponseEntity.ok(bannerList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Data Not Found Here !!!"));
        }
    }


    @DeleteMapping(BannerUrlMapping.DELETE_BANNER_BY_ID)
    public ResponseEntity<?> deleteBannerById(@PathVariable("bannerId")String bannerId )
    {
        if(this.bannerImple.deleteBannerById(bannerId))
        {
            return  ResponseEntity.ok(new MessageResponse("Deleted"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Data Not Found Here !!!"));
        }
    }


}
