package com.bezkoder.springjwt.urlMappings;


import org.springframework.stereotype.Component;

@Component
public class URLMappings {

    //SIGN-IN URL's
    public static final String AUTH_ACCESS_URL="/api/auth";

    //ADMIN ACCESS URL's
    public static final String AUTH_ACCESS_URL_ADMIN="/api/admin";

    public static final String API_PUBLIC_BUCKET="/api/public/bucket";


    //Product Root Category
    public static final String SAVE_PRODUCT_ROOT_CATEGORY="/saveProductRootCategory";
    public static final String GET_ROOT_CATEGORY_LIST="/getRootCategoryList";
    public static final String REMOVE_ROOT_CATEGORY="/removeRootCategory/{rootCategoryId}";
    public static final String GET_ROOT_CATEGORY_BY_ID="/getRootCategoryById/{rootCategoryId}";
    public static final String UPDATE_ROOT_CATEGORY="/updateRootCategory";


    //Product Sub Category
    public static final String ADD_PRODUCT_SUB_CATEGORY="/addProductSubCategory";
    public static final String GET_SUB_CATEGORY_LIST="/getSubCategoryList";
    public static final String REMOVE_SUB_CATEGORY="/removeSubCategory/{subCategoryId}";
    public static final String UPDATE_SUB_CATEGORY="/updateSubCategory";
    public static final String GET_SUB_CATEGORY_BY_ID="/getSubCategoryById/{subCategoryId}";


    //Product FINAL Category
    public static final String SAVE_FINAL_PRODUCT_CATEGORY="/saveFinalProductCategory";
    public static final String GET_FINAL_CATEGORY_LIST="/getFinalCategoryList";
    public static final String UPDATE_FINAL_CATEGORY="/updateFinalCategory";
    public static final String GET_FINAL_CATEGORY_BY_ID="/getFinalCategoryById/{finalCategoryId}";


    //PRODUCT
    public static final String SAVE_PRODUCT="/saveProduct";
    public static final String DELETE_PRODUCT_BY_ID="/deleteProductById/{productId}";
    public static final String GET_PRODUCT_LIST="/getProductList";
    public static final String GET_PRODUCT_BY_ID="/getProductById/{productId}";
    public static final String GET_PRODUCT_FILES_BY_PRODUCT_ID="/getProductFilesByProductId/{productId}";
    public static final String SET_PRODUCT_THUMBNAIL="/setProductThumbNail/{productId}/{bucketId}";
    public static final String SET_THUMBNAIL_MAIN_PAGE="/setThumbNailMainPage/{productId}/{bucketId}";



}
