package com.bezkoder.springjwt.paymentController.razorpay.razorpayRepo;

import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.RazorPayOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RazorpayOrderRepo extends JpaRepository<RazorPayOrder,Long> {

    public RazorPayOrder findByOrderId(String orderId);
}
