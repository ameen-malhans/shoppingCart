package com.cart.repository;

import com.cart.model.ShoppingCart;
import com.cart.model.User;
import com.cart.model.LineItem;
import com.cart.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface LineItemRepository extends JpaRepository<LineItem, Long> {
	
}
