package com.cart.service;

import java.security.NoSuchAlgorithmException;

import com.cart.controller.exception.AuthenticationFailedException;
import com.cart.model.User;


public interface UserService {

	User authentication(String username, String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException;
	Long addUser(User user) throws NoSuchAlgorithmException;
	User getUser(Long userId) throws AuthenticationFailedException;
	boolean isUserExist(String username);
}
