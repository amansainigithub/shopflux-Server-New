package com.bezkoder.springjwt.interfaces.bannerInterface;

import com.bezkoder.springjwt.entities.bannerEntities.BannerTypeForm;

import java.util.List;

public interface BannerType {

    public BannerTypeForm saveBannerType(BannerTypeForm bannerTypeForm);

    public List<BannerTypeForm> getBannerType();


}
