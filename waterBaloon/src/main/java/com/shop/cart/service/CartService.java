package com.shop.cart.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.cart.entity.Cart;
import com.shop.cart.entity.CartItem;
import com.shop.cart.repository.CartRepository;
import com.shop.cart.repository.CartItemRepository;
import com.shop.item.entity.Item;
import com.shop.item.repository.ItemRepository;
import com.shop.user.entity.Users;
import com.shop.user.repository.UserRepository;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private UserRepository userRepository;
	@Transactional
	public Cart getCart(Users user) {
		return cartRepository.findByUser(user);
	}
	@Transactional
	public void addItemToCart(Users user, Long itemId, int quantity) {
		Cart cart = cartRepository.findByUser(user);
		if (cart == null) {
			cart = new Cart();
			cart.setUser(user);
			cartRepository.save(cart);
		}

		Item item = itemRepository.findById(itemId).orElseThrow(() -> new RuntimeException("아이템을 찾을 수 없습니다..."));
		Optional<CartItem> existingCartItem = cart.getItems().stream()
				.filter(cartItem -> cartItem.getItem().equals(item)).findFirst();

		if (existingCartItem.isPresent()) {
			CartItem cartItem = existingCartItem.get();
			cartItem.setQuantity(cartItem.getQuantity() + quantity);
			cartItemRepository.save(cartItem);
		} else {
			CartItem cartItem = new CartItem();
			cartItem.setItem(item);
			cartItem.setCart(cart);
			cartItem.setQuantity(quantity);
			cart.getItems().add(cartItem);
			cartItemRepository.save(cartItem);
		}
	}
	@Transactional
	public void removeItemsFromCart(List<Long> cartItemIds) {
		for (Long cartItemId : cartItemIds) {
			cartItemRepository.deleteById(cartItemId);
		}
	}
}
