package com.shop.cart.entity;

import java.time.LocalDateTime;

import com.shop.item.entity.Item;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private Item item;

	@ManyToOne
	@JoinColumn(name = "cart_id", nullable = false)
	private Cart cart;

	private int quantity;

	private LocalDateTime createdTime;

	@PrePersist
	protected void onCreate() {
		createdTime = LocalDateTime.now();
	}
}
