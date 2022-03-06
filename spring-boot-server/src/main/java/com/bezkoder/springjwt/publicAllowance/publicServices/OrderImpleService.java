package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.OrderInterfacePublic;
import com.bezkoder.springjwt.repositories.cartRepo.CartPaidRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class OrderImpleService  implements OrderInterfacePublic {

    @Autowired
    private CUService cuService;

    @Autowired
    private CartPaidRepo cartPaidRepo;

    @Override
    public List<CartCatcherPaidForm> getProductOrderCurrentUser(String currentUser,HttpServletRequest request) {

        try {
            User user =  this.cuService.getCurrentUser_service(request);

            if(user.getUsername().equals(currentUser.trim()))
            {
                return this.cartPaidRepo.findByUserName(user.getUsername().trim());
            }
           else
           {
                return null;
           }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
