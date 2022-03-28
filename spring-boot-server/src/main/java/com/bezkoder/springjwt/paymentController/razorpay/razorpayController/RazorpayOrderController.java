package com.bezkoder.springjwt.paymentController.razorpay.razorpayController;

import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.RazorPayOrder;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayService.RazorPayService;
import com.bezkoder.springjwt.urlMappings.PaymentUrlMapping;
import com.bezkoder.springjwt.urlMappings.URLMappings;
import com.razorpay.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(URLMappings.AUTH_ACCESS_URL_ADMIN)
public class RazorpayOrderController {


    @Autowired
    private RazorPayService razorPayService;

    @GetMapping(PaymentUrlMapping.RAZORPAY_GET_ORDER_DETAILS_BY_ORDER_ID)
    public ResponseEntity<?> razorpayGetOrderDetailsByOrderId(@PathVariable String orderId){
        RazorPayOrder razorPayOrder =  this.razorPayService.razorpayGetOrderDetailsByOrderId(orderId);
        if(razorPayOrder != null )
        {
            return  ResponseEntity.ok(razorPayOrder);
        }
        else
        {
            return  ResponseEntity.ok(new MessageResponse("Something Went Wrong !!! "));
        }

    }

}
