package com.bezkoder.springjwt.repositories.bannerRepo;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerRepository extends JpaRepository<BannerForm,Long> {
}
