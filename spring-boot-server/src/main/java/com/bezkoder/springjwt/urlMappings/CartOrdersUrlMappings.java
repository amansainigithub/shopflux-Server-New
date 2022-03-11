package com.bezkoder.springjwt.urlMappings;

import org.springframework.stereotype.Component;

@Component
public class CartOrdersUrlMappings {

    public static final String GET_CURRENT_PAID_ORDERS="/getCurrentPaidOrders";
    public static final String GET_CURRENT_PAID_ORDERS_BY_ID="/getCurrentPaidOrdersById/{orderId}";
    public static final String UPDATE_CURRENT_PAID_ORDERS="/updateCurrentPaidOrder";


}
