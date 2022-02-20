package com.bezkoder.springjwt.repositories.productCategoryRepository;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;
import com.bezkoder.springjwt.interfaces.productCategoryInterfaces.ProductFinalCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductFinalCategoryRepository extends JpaRepository<ProductFinalCateogoryForm,Long> {

   public  List<ProductFinalCateogoryForm> findBySubCategoryId(String subCategoryId);

   public  ProductFinalCateogoryForm findByfinalCategoryName(String finalCategoryName);
}
