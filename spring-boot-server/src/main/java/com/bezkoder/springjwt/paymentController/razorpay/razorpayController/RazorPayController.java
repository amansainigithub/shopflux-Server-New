package com.bezkoder.springjwt.paymentController.razorpay.razorpayController;

import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.RazorPayOrder;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.UpdateRazorPayOrder;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayService.RazorPayService;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.urlMappings.PaymentUrlMapping;
import com.razorpay.Order;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class RazorPayController {

    @Autowired
    private RazorPayService razorPayService;


//    @PostMapping(PaymentUrlMapping.RP_CREATE_ORDER)
//    public ResponseEntity<?> createBanner(@RequestBody Map<String,String> data,HttpServletRequest request) throws InterruptedException, RazorpayException {
//        Object data1 =  this.razorPayService.createRazorPayOrder(data,request);
//        if(data1 != null )
//        {
//            return  ResponseEntity.ok(data1);
//        }
//        else
//        {
//            return  ResponseEntity.ok(new MessageResponse("Banner Not Created !!! "));
//        }
//
//    }

        @PostMapping(PaymentUrlMapping.UPDATE_RAZORPAY_ORDER)
    public ResponseEntity<?> updateRazorPayOrder(@RequestBody UpdateRazorPayOrder updateRazorPayOrder){
            System.out.println("running");
            RazorPayOrder rpo =  this.razorPayService.updateRazorPayOrderService(updateRazorPayOrder);
        if(rpo != null )
        {
            return  ResponseEntity.ok(rpo);
        }
        else
        {
            return  ResponseEntity.ok(new MessageResponse("Something Went Wrong !!! "));
        }

    }










}
