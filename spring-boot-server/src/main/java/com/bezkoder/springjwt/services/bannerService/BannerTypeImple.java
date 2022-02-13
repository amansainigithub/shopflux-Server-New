package com.bezkoder.springjwt.services.bannerService;

import com.bezkoder.springjwt.entities.bannerEntities.BannerTypeForm;
import com.bezkoder.springjwt.interfaces.bannerInterface.BannerType;
import com.bezkoder.springjwt.repositories.bannerRepo.BannerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerTypeImple  implements BannerType {

    @Autowired
    private BannerTypeRepository bannerTypeRepository;

    @Override
    public BannerTypeForm saveBannerType(BannerTypeForm bannerTypeForm) {
        BannerTypeForm bannerTypeFormData=null;
        try {
           bannerTypeFormData =  this.bannerTypeRepository.save(bannerTypeForm);
        }
        catch (Exception e)
        {
        e.printStackTrace();
        }
        return bannerTypeForm;
    }

    @Override
    public List<BannerTypeForm> getBannerType() {
      return  this.bannerTypeRepository.findAll();
    }
}
