package com.bezkoder.springjwt.publicAllowance.publicInterfaces;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;

import java.util.List;

public interface BannerInterfacePublic {

    public List<BannerForm> getBannerList();

    public List<BannerForm> getTopBannerList();
}
