package com.shop.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.shop.cart.entity.Cart;
import com.shop.cart.service.CartService;
import com.shop.user.entity.Users;
import com.shop.user.repository.UserRepository;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public String addItemToCart(@RequestParam("itemId") Long itemId, @RequestParam("quantity") int quantity, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Users user = userRepository.findByUsername(username);
            if (user != null) {
                cartService.addItemToCart(user, itemId, quantity);
                System.out.println("Item added to cart: " + itemId + " Quantity: " + quantity);
            } else {
                System.out.println("User not found: " + username);
            }
        } else {
            System.out.println("User not authenticated");
        }
        return "redirect:/cart";
    }

    @GetMapping
    public String getCart(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Users user = userRepository.findByUsername(username);
            if (user != null) {
                Cart cart = cartService.getCart(user);
                model.addAttribute("cart", cart);
            } else {
                System.out.println("User not found: " + username);
            }
        } else {
            System.out.println("User not authenticated");
        }
        return "cart";
    }

    @PostMapping("/remove")
    public String removeItemsFromCart(@RequestParam("cartItemIds") List<Long> cartItemIds, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Users user = userRepository.findByUsername(username);
            if (user != null) {
                cartService.removeItemsFromCart(cartItemIds);
                System.out.println("Items removed from cart: " + cartItemIds);
            } else {
                System.out.println("User not found: " + username);
            }
        } else {
            System.out.println("User not authenticated");
        }
        return "redirect:/cart";
    }
}
