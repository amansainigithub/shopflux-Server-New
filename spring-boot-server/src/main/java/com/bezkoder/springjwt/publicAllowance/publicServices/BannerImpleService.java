package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.BannerInterfacePublic;
import com.bezkoder.springjwt.repositories.bannerRepo.BannerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BannerImpleService implements BannerInterfacePublic {

    @Autowired
    private BannerRepository bannerRepository;

    @Override
    public List<BannerForm> getBannerList() {
        List<BannerForm> bannerData=null;
       try {
         bannerData =   this.bannerRepository.findAll();
       }catch (Exception e)
       {
           e.printStackTrace();
       }
       return bannerData;
    }

    @Override
    public List<BannerForm> getTopBannerList() {
        List<BannerForm> bannerData=null;
        try {
            bannerData =   this.bannerRepository.findByBannerType("TOP_BANNER");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return bannerData;
    }
}
