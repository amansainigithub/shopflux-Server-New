package com.bezkoder.springjwt.interfaces.productCategoryInterfaces;

import com.bezkoder.springjwt.entities.productEntities.ProductForm;

import java.util.List;

public interface ProductCategory {


    public ProductForm saveProduct(ProductForm productForm);

    public boolean deleteProduct(long productId);

    public List<ProductForm> getProductList();

    public ProductForm getProductById(long productId );

    public boolean setProductThumbnail(String productId,String bucketId );

    public boolean setThumbnailMainPage(String productId,String bucketId );


}
