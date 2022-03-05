package com.bezkoder.springjwt.repositories.cartRepo;

import com.bezkoder.springjwt.entities.cartEntities.CartCatcherForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartCatcherForm,Long> {
}
