package com.bezkoder.springjwt.publicAllowance.publicInterfaces;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.razorpay.Order;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface CartInterfacePublic {
    public Order cartCatcherService(List<CartCatcherForm> cartCatcherList , HttpServletRequest request);

  public boolean updateCartCatcher(List<CartCatcherPaidForm> cartCatcherPaidForms, HttpServletRequest request);
}
