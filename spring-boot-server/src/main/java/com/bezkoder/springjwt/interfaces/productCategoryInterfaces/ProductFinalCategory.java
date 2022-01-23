package com.bezkoder.springjwt.interfaces.productCategoryInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductFinalCateogoryForm;

import java.util.List;

public interface ProductFinalCategory {

    public ProductFinalCateogoryForm saveFinalProductCategory(ProductFinalCateogoryForm productFinalCateogoryForm);

    public List<ProductFinalCateogoryForm> getFinalCategoryList();

    public  ProductFinalCateogoryForm updateFinalCategory(ProductFinalCateogoryForm productFinalCateogoryForm);

    public  ProductFinalCateogoryForm getFinalCategoryById(long productFinalCategoryId);
}
