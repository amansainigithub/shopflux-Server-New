package com.bezkoder.springjwt.publicAllowance.publicController.orderController;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.helper.email.EmailSender;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.publicAllowance.publicServices.OrderImpleService;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.CartUrlMappings;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class OrderControllerPublic {

    @Autowired
    private OrderImpleService orderImpleService;

    @Autowired
    private EmailSender emailSender;

    @GetMapping(CartUrlMappings.GET_PAID_ORDER_PUBLIC)
    public ResponseEntity<?> getPaidOrder_public( @PathVariable String currentUser, HttpServletRequest request) throws MessagingException, IOException {
        List<CartCatcherPaidForm>  cartOrderList =   this.orderImpleService.getProductOrderCurrentUser(currentUser,request);
        if(cartOrderList != null)
        {
            return   ResponseEntity.ok(cartOrderList);
        }
        else {
            return   ResponseEntity.ok(new MessageResponse("FAILED"));
        }

    }
}
