package com.bezkoder.springjwt.repositories.cartRepo;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherPaidForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CartPaidRepo extends JpaRepository<CartCatcherPaidForm,Long> {

    public List<CartCatcherPaidForm> findByUserName(String userName);
    public List<CartCatcherPaidForm> findByDeliveryStatus(String status);
}
