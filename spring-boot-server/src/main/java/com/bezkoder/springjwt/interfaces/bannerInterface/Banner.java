package com.bezkoder.springjwt.interfaces.bannerInterface;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import org.springframework.web.multipart.MultipartFile;

public interface Banner {

    public BannerForm createBanner(BannerForm bannerForm);

    public boolean uploadBannerFileByBannerId(String bannerId, MultipartFile file);
}
