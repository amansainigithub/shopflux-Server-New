package com.bezkoder.springjwt.interfaces.cartOrderInterface;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;

import java.util.List;

public interface CartOrderInterface {


    public List<CartCatcherPaidForm> getPaidCurrentOrderList();

    public CartCatcherPaidForm getPaidCurrentOrderBYId(String orderId);

    CartCatcherPaidForm updateCurrentPaidOrder(CartCatcherPaidForm cartCatcherPaidForm);
}
