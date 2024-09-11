package com.shop.cart.repository;

import com.shop.cart.entity.Cart;
import com.shop.user.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    Cart findByUser(Users user);
}
