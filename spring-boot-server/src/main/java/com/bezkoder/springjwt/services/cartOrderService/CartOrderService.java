package com.bezkoder.springjwt.services.cartOrderService;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.helper.email.EmailSender;
import com.bezkoder.springjwt.interfaces.cartOrderInterface.CartOrderInterface;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayModels.RazorPayOrder;
import com.bezkoder.springjwt.paymentController.razorpay.razorpayRepo.RazorpayOrderRepo;
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

    @Autowired
    private RazorpayOrderRepo razorpayOrderRepo;

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
            if(cartCatcherPaidForm.getDeliveryStatus().equals("N")) //DELIVERY PENDING
            {
                this.changeDateFormat(cartCatcherPaidForm.getDeliveryDate(), cartCatcherPaidForm);
                ccpf =    this.cartPaidRepo.save(cartCatcherPaidForm);
            }
            else if(cartCatcherPaidForm.getDeliveryStatus().equals("Y")) //DELIVERY COMPLETED
            {
                cartCatcherPaidForm.setDeliveryStatus("Y");
                ccpf  =    this.cartPaidRepo.save(cartCatcherPaidForm);
                RazorPayOrder razorPayOrder =  this.razorpayOrderRepo.findByOrderId(cartCatcherPaidForm.getRazorOrderId());
                cartCatcherPaidForm.setProductPrice(String.valueOf(Integer.parseInt(razorPayOrder.getAmount())/100));
                this.emailTriggerIfDeliverySuccess(cartCatcherPaidForm);
            }
            else
            {
                this.changeDateFormat(cartCatcherPaidForm.getDeliveryDate(), cartCatcherPaidForm);
                cartCatcherPaidForm.setDeliveryStatus("R"); //DELIVERY RUNNING MODE
                ccpf =    this.cartPaidRepo.save(cartCatcherPaidForm);
                RazorPayOrder razorPayOrder =  this.razorpayOrderRepo.findByOrderId(cartCatcherPaidForm.getRazorOrderId());
                cartCatcherPaidForm.setProductPrice(String.valueOf(Integer.parseInt(razorPayOrder.getAmount())/100));
                this.emailTrigger(cartCatcherPaidForm);
            }

        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return ccpf;
    }



    //EMAIL-TRIGGER
    public boolean emailTrigger(CartCatcherPaidForm cartCatcherPaidForm)
    {
        boolean flag= false;
        try {

            String content="<h3>E-COMM - DELIVERY RUNNING -- [DELIVERY DATE ] :"+cartCatcherPaidForm.getDeliveryDateFormat()+"</h3>\r\n"
                    + "\r\n"
                    + "\r\n"
                    + "<table style=\"width:100%;border: 1px solid black;\">\r\n"
                    + "  <tr style=\"border: 1px solid black;\">\r\n"
                    + "    <th style=\"border: 1px solid black;\">Email</th>\r\n"
                    + "    <th style=\"border: 1px solid black;\">Date</th> \r\n"
                    + "    <th style=\"border: 1px solid black;\">Delivery Company</th>\r\n"
                    + "     <th style=\"border: 1px solid black;\">Price</th>\r\n"
                    + "     <th style=\"border: 1px solid black;\">Delivery-Order-ID</th>\r\n"
                    + "     <th style=\"border: 1px solid black;\">Product-URL</th>\r\n"
                    + "  </tr>\r\n"
                    + "  <tr>\r\n"
                    + "    <td style=\"border: 1px solid black;\">"+cartCatcherPaidForm.getEmail()+"</td>\r\n"
                    + "    <td style=\"border: 1px solid black;\">"+cartCatcherPaidForm.getDeliveryDateFormat()+"</td>\r\n"
                    + "    <td style=\"border: 1px solid black;\">"+cartCatcherPaidForm.getDeliveryOrderCompanyName()+"</td>\r\n"
                    + "    <td style=\"border: 1px solid black;\">"+cartCatcherPaidForm.getProductPrice()+"</td>\r\n"
                    + "    <td style=\"border: 1px solid black;\">"+cartCatcherPaidForm.getDeliveryOrderId() +"</td>\r\n"
                    + "    <td style=\"border: 1px solid black;\">"+cartCatcherPaidForm.getUrl() +"</td>\r\n"
                    + "  </tr>\r\n"
                    + " \r\n"
                    + "</table>"
            ;

            EmailSender.sendEmail(cartCatcherPaidForm.getEmail(),content);
            flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean emailTriggerIfDeliverySuccess(CartCatcherPaidForm cartCatcherPaidForm)
    {
        boolean flag= false;
        try {
        String content ="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "<style>\n" +
                ".card {\n" +
                "  box-shadow: 0 4px 8px 0 rgba(0,0,0,0.2);\n" +
                "  transition: 0.3s;\n" +
                "  width: 40%;\n" +
                "}\n" +
                "\n" +
                ".card:hover {\n" +
                "  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);\n" +
                "}\n" +
                "\n" +
                ".container {\n" +
                "  padding: 2px 16px;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Card</h2>\n" +
                "\n" +
                "<div class=\"card\">\n" +
                "  \n" +
                "  <div class=\"container\">\n" +
                "    <h4><b>DELIVERED SUCCESS</b></h4> \n" +
                "     <p>DELIVERY DATE : "+cartCatcherPaidForm.getDeliveryDateFormat()+"</p> \n" +
                "     <p>PRODUCT-URL : "+cartCatcherPaidForm.getUrl()+"</p> \n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html> \n";
            EmailSender.sendEmail(cartCatcherPaidForm.getEmail(),content);
            flag=true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;

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


    @Override
    public List<CartCatcherPaidForm> getCurrentPaidOrdersByStatus(String status) {
        List<CartCatcherPaidForm> conditionalList = null;
        try {
            conditionalList =   this.cartPaidRepo.findByDeliveryStatus(status);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conditionalList;
    }


}
