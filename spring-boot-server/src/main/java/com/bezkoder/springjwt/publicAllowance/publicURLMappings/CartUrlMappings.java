package com.bezkoder.springjwt.publicAllowance.publicURLMappings;

import org.springframework.stereotype.Component;

@Component
public class CartUrlMappings {
    public static final String GET_PAID_ORDER_PUBLIC="getPaidOrder_public/{currentUser}";

}
