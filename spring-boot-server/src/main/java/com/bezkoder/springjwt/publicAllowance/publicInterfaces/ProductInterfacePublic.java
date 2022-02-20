package com.bezkoder.springjwt.publicAllowance.publicInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductForm;

import java.util.List;

public interface ProductInterfacePublic {
    public List<ProductForm> getProductList();

    public List<ProductForm> getProductByFinalCategory();

    List<ProductForm> getProductListByFinalCategoryName(String finalCategoryName);
}
