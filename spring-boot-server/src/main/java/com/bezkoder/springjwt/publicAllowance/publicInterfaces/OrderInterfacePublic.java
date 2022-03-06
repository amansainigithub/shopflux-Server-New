package com.bezkoder.springjwt.publicAllowance.publicInterfaces;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface OrderInterfacePublic {

    public List<CartCatcherPaidForm> getProductOrderCurrentUser(String currentUser,HttpServletRequest request);
}
