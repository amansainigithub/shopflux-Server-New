package com.bezkoder.springjwt.adminControllers.cartOrderController;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.entities.productEntities.ProductForm;
import com.bezkoder.springjwt.services.cartOrderService.CartOrderService;
import com.bezkoder.springjwt.urlMappings.CartOrdersUrlMappings;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class CartOrderController {

    @Autowired
    private CartOrderService cartOrderService;

    @GetMapping(CartOrdersUrlMappings.GET_CURRENT_PAID_ORDERS)
    public ResponseEntity<?> getCurrentPaidOrders()
    {
        List<CartCatcherPaidForm>   currentOrders = this.cartOrderService.getPaidCurrentOrderList();
        if(currentOrders != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(currentOrders);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CartOrdersUrlMappings.GET_CURRENT_PAID_ORDERS_BY_ID)
    public ResponseEntity<?> getCurrentPaidOrders(@PathVariable String orderId)
    {
        CartCatcherPaidForm  order = this.cartOrderService.getPaidCurrentOrderBYId(orderId);
        if(order != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(order);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping(CartOrdersUrlMappings.UPDATE_CURRENT_PAID_ORDERS)
    public ResponseEntity<?> updateCurrentPaidOrder(@RequestBody CartCatcherPaidForm cartCatcherPaidForm) throws ParseException {
        System.out.println("*************************************");

        CartCatcherPaidForm  updatedPaidOrder = this.cartOrderService.updateCurrentPaidOrder(cartCatcherPaidForm);
        if(updatedPaidOrder != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(updatedPaidOrder);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }



    @GetMapping(CartOrdersUrlMappings.GET_CURRENT_PAID_ORDERS_BY_STATUS)
    public ResponseEntity<?> getCurrentPaidOrdersByStatus(@PathVariable String status)
    {
        List<CartCatcherPaidForm>  list = this.cartOrderService.getCurrentPaidOrdersByStatus(status);
        if(list != null)
        {
            return ResponseEntity.status(HttpStatus.OK).body(list);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }







}
