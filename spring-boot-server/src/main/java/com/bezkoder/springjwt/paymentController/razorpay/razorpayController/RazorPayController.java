package com.bezkoder.springjwt.paymentController.razorpay.razorpayController;

import com.bezkoder.springjwt.entities.bannerEntities.BannerForm;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayService.RazorPayService;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.urlMappings.PaymentUrlMapping;
import com.razorpay.RazorpayException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class RazorPayController {

    @Autowired
    private RazorPayService razorPayService;


    @PostMapping(PaymentUrlMapping.RP_CREATE_ORDER)
    public ResponseEntity<?> createBanner(@RequestBody Map<String,String> data) throws InterruptedException, RazorpayException {
        Object data1 =  this.razorPayService.createRazorPayOrder(data);
        if(data1 != null )
        {
            return  ResponseEntity.ok(data1);
        }
        else
        {
            return  ResponseEntity.ok(new MessageResponse("Banner Not Created !!! "));
        }

    }






}
