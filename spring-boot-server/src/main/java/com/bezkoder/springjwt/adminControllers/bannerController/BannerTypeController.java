package com.bezkoder.springjwt.adminControllers.bannerController;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.entities.bannerEntities.BannerTypeForm;
import com.bezkoder.springjwt.interfaces.bannerInterface.BannerType;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.services.bannerService.BannerTypeImple;
import com.bezkoder.springjwt.urlMappings.BannerUrlMapping;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class BannerTypeController {

    @Autowired
    private BannerTypeImple bannerTypeImple;


    @PostMapping(BannerUrlMapping.ADD_BANNER_TYPE)
    public ResponseEntity<?> addBannerType(@Valid @RequestBody BannerTypeForm bannerTypeForm) throws InterruptedException {
        BannerTypeForm saveBannerType =  this.bannerTypeImple.saveBannerType(bannerTypeForm);

        if(saveBannerType != null )
        {
            return  ResponseEntity.ok(new MessageResponse("Banner Type Successfully Created !!!"));
        }
        else
        {
            return  ResponseEntity.ok(new MessageResponse("Banner Not Created !!! "));
        }

    }

    @GetMapping(BannerUrlMapping.GET_BANNER_TYPE_LIST)
    public ResponseEntity<?> getBannerTypeList() throws InterruptedException {

        List<BannerTypeForm> bannerTypeList=  this.bannerTypeImple.getBannerType();
        if(!bannerTypeList.isEmpty())
        {
            return  ResponseEntity.ok(bannerTypeList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse("Data Not Found Here !!!"));
        }

    }


}
