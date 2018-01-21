package com.cart.repository;

import com.cart.model.AttributeValue;
import com.cart.model.ShoppingCart;
import com.cart.model.User;
import com.cart.model.Product;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
	
	Set<AttributeValue> getProductDetailById(Long productId);
}
