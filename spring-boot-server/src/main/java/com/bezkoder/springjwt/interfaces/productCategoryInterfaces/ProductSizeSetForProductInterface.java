package com.bezkoder.springjwt.interfaces.productCategoryInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductSizeSetForProductForm;

import java.util.List;

public interface ProductSizeSetForProductInterface {


    ProductSizeSetForProductForm saveProductSizeSetProductForm(String productId,ProductSizeSetForProductForm productSizeSetForProductForm);

    boolean removeProductSizeForProductById(String productSizeId);

    ProductSizeSetForProductForm getProductSizeForProductById(String productSizeId);


    // List<ProductSizeSetForProductForm> getProductSizeListByProductId(String productId);
}
