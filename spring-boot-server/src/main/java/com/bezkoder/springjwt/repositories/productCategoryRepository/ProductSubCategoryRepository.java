package com.bezkoder.springjwt.repositories.productCategoryRepository;

import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductSubCategoryRepository extends JpaRepository<ProductSubCategoryForm,Long> {

    ProductSubCategoryForm findByproductSubCategoryId(long productSubCategoryId);
}
