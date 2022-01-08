package com.bezkoder.springjwt.repositories.productCategoryRepository;

import com.bezkoder.springjwt.entities.productEntities.ProductFileUrls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFileUrlsRepository extends JpaRepository<ProductFileUrls,Long> {

    List<ProductFileUrls> findByproductId(String productId);

}
