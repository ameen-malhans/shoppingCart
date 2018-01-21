package com.cart.repository;

import com.cart.model.ShoppingCart;
import com.cart.model.User;
import com.cart.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(@Param("username") String username);
}
