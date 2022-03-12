package com.bezkoder.springjwt.services.cartOrderService;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.interfaces.cartOrderInterface.CartOrderInterface;
import com.bezkoder.springjwt.repositories.cartRepo.CartPaidRepo;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class CartOrderService implements CartOrderInterface {

    @Autowired
    private CartPaidRepo cartPaidRepo;

    @Override
    public List<CartCatcherPaidForm> getPaidCurrentOrderList(){
        List<CartCatcherPaidForm> orderList = null;
        try {
            orderList =   this.cartPaidRepo.findAll(Sort.by(Sort.Direction.DESC,"id"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return orderList;
    }

    @Override
    public CartCatcherPaidForm getPaidCurrentOrderBYId(String orderId) {
        CartCatcherPaidForm order = null;
        try {
            order =   this.cartPaidRepo.findById(Long.parseLong(orderId)).get();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public CartCatcherPaidForm updateCurrentPaidOrder(CartCatcherPaidForm cartCatcherPaidForm) {
        CartCatcherPaidForm ccpf = null;
        try {
            if(cartCatcherPaidForm.getDeliveryStatus().equals("N"))
            {
                this.changeDateFormat(cartCatcherPaidForm.getDeliveryDate(), cartCatcherPaidForm);
                ccpf =    this.cartPaidRepo.save(cartCatcherPaidForm);
            }
            else if(cartCatcherPaidForm.getDeliveryStatus().equals("Y"))
            {
                cartCatcherPaidForm.setDeliveryStatus("Y");
                ccpf =    this.cartPaidRepo.save(cartCatcherPaidForm);
            }
            else
            {
                this.changeDateFormat(cartCatcherPaidForm.getDeliveryDate(), cartCatcherPaidForm);
                cartCatcherPaidForm.setDeliveryStatus("R");
                ccpf =    this.cartPaidRepo.save(cartCatcherPaidForm);
            }

        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return ccpf;
    }



    public  void  changeDateFormat(String dateReceiver,CartCatcherPaidForm cartCatcherPaidForm)
    {
        String finalDate = null;
        try {

            //DATE SPLITTER
            String beforeDateStr =  dateReceiver.substring(0,8);
            String leftDateString =  dateReceiver.substring(10,dateReceiver.length());
            String dayMerging =String.valueOf(Integer.parseInt(dateReceiver.substring(8,10))+1);

            String year = dateReceiver.substring(0,4);
            String month = dateReceiver.substring(5,7);
            String day = dateReceiver.substring(8,10);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = formatter.parse(dayMerging+"/"+month+"/"+year);
            System.out.println("DATE : " + date);
            cartCatcherPaidForm.setDeliveryDateFormat(date.toString());

            //CONCAT DATE
            finalDate =  beforeDateStr + dayMerging + leftDateString;

            cartCatcherPaidForm.setDeliveryDate(finalDate);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
