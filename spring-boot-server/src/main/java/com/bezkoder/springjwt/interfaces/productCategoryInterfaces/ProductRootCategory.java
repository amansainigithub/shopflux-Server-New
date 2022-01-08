package com.bezkoder.springjwt.interfaces.productCategoryInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductRootCategoryForm;

import java.util.List;

public interface ProductRootCategory {

    public ProductRootCategoryForm saveProductRootCategory(ProductRootCategoryForm productRootCategoryForm);

    public List<ProductRootCategoryForm> getRootCategoryList();

    public boolean removeRootCategory(long rootCategoryid);

    public ProductRootCategoryForm getRootCategoryById(long rootCategoryid);

    public boolean updateRootCategory(ProductRootCategoryForm productRootCategoryForm);


}
