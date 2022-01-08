package com.bezkoder.springjwt.repositories.productCategoryRepository;

import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRootCategoryRepository  extends JpaRepository<ProductRootCategoryForm,Long> {

//    ProductRootCategoryForm findById(long rootCategoryId);

}
