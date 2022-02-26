package com.bezkoder.springjwt.repositories.productCategoryRepository;

import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<ProductForm,Long> {

    public ProductForm  findByproductSKU(String productSKU);

    public ProductForm  findByproductUSN(String productUSN);

    public ProductForm findByproductName(String productName);

    public List<ProductForm> findByRootCategoryId(String rootCategoryId);

    public List<ProductForm> findBySubCategoryId(String subCategoryId);

    public List<ProductForm> findByFinalCategoryId(String finalCategoryId);


}
