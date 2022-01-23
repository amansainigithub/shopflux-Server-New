package com.bezkoder.springjwt.repositories.productCategoryRepository;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFinalCategoryRepository extends JpaRepository<ProductFinalCateogoryForm,Long> {
}
