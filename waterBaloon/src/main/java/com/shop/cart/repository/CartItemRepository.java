package com.shop.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shop.cart.entity.Cart;
import com.shop.cart.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	 List<CartItem> findByCartOrderByCreatedTimeAsc(Cart cart);
}
