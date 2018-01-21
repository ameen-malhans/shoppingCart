package com.cart.service;

import com.cart.model.ShoppingCart;
import com.cart.model.User;

public interface ShoopingCartService {

	Long save(ShoppingCart cart);
	void add(Long idCart, Long idProduct, Integer quantity);
	void removeItemFromCart(Long idCart,Long idProduct);
	ShoppingCart getShoopingCartByUser(User user);
}
