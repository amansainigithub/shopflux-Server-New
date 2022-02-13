package com.bezkoder.springjwt.repositories.bannerRepo;

import com.bezkoder.springjwt.entities.bannerEntities.BannerTypeForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BannerTypeRepository extends JpaRepository<BannerTypeForm,Long> {


}
