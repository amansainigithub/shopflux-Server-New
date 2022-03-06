package com.bezkoder.springjwt.publicAllowance.publicURLMappings;

import org.springframework.stereotype.Component;

@Component
public class ProductUrlMappingPublic {

    public static final String GET_PRODUCT_LIST_PUBLIC="getProductList_public";

    public static final String GET_PRODUCT_BY_FINAL_CATEGORY="getProductByFinalCategory_public";

    public static final String GET_PRODUCT_BY_FINAL_CATEGORY_NAME_PUBLIC="getProductListByFinalCategoryName_public/{finalCategoryName}";

    public static final String GET_PRODUCT_BY_PRODUCT_ID="getProductByProductId/{productId}";

    public static final String GET_PRODUCT_BY_FINAL_CATEGORY_ID="getProductByFinalCategoryId/{finalCategoryId}";

}
