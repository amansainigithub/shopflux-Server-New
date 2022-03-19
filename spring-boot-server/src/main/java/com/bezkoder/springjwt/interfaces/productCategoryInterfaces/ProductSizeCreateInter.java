package com.bezkoder.springjwt.interfaces.productCategoryInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductSizeCreateForm;

import java.util.List;

public interface ProductSizeCreateInter {
    ProductSizeCreateForm createProductSize(ProductSizeCreateForm productSizeCreateForm);

    public boolean deleteProductSize(String id);

    ProductSizeCreateForm getProductDetailsSizeByid(String sizeId);

    List<ProductSizeCreateForm> getProductSizeList();
}
