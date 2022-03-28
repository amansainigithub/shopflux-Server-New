package com.bezkoder.springjwt.urlMappings;

import org.springframework.stereotype.Component;

@Component
public class CartOrdersUrlMappings {

    public static final String GET_CURRENT_PAID_ORDERS="/getCurrentPaidOrders";
    public static final String GET_CURRENT_PAID_ORDERS_BY_ID="/getCurrentPaidOrdersById/{orderId}";
    public static final String UPDATE_CURRENT_PAID_ORDERS="/updateCurrentPaidOrder";
    public static final String GET_CURRENT_PAID_ORDERS_BY_STATUS="/getCurrentPaidOrdersByStatus/{status}";



    public static final String SAVE_PRODUCT_SIZE_SET_FOR_PRODUCT = "/saveProductSizeSetForProduct/{productId}";
    public static final String REMOVE_PRODUCT_SIZE_FOR_PRODUCT_BY_ID = "/removeProductSizeForProductById/{productSizeId}";
    public static final String GET_PRODUCT_SIZE_FOR_PRODUCT_BY_ID = "/getProductSizeForProductById/{productSizeId}";


}
