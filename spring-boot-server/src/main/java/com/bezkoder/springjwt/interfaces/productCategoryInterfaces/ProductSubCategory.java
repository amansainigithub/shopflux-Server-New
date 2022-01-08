package com.bezkoder.springjwt.interfaces.productCategoryInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;
import com.bezkoder.springjwt.entities.productEntities.ProductSubCategoryForm;

import java.util.List;

public interface ProductSubCategory {

    public ProductSubCategoryForm addProductSubCategory(ProductSubCategoryForm productSubCategoryForm);

    public List<ProductSubCategoryForm> getProductSubCategoryList();

    public boolean removeSubCategory(long rootCategoryId);

    public ProductSubCategoryForm getSubCategoryById(long subCategoryId);

}
