package com.cart.service.impl;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.controller.exception.AuthenticationFailedException;
import com.cart.model.User;
import com.cart.repository.UserRepository;
import com.cart.service.UserService;
import com.cart.util.ShoppingCartUtils;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository customerDao;
	
	@Override
	public User authentication(String username, String password) 
			throws NoSuchAlgorithmException, AuthenticationFailedException {
		User customer = customerDao.findByUsername(username);
		if(customer!=null && customer.getPassword().equals(ShoppingCartUtils.encrypted(password)))
			return customer;
		else
			throw new AuthenticationFailedException();
	}

	@Override
	public Long addUser(User user) throws NoSuchAlgorithmException {
		user.setPassword(ShoppingCartUtils.encrypted(user.getPassword()));
		return customerDao.save(user).getIdcustomer();
	}

	@Override
	public User getUser(Long userId) throws AuthenticationFailedException{
		User customer = customerDao.findOne(userId);
		if(customer==null){
			throw new AuthenticationFailedException();
		}
		return customer;
	}

	@Override
	public boolean isUserExist(String username){
		User user = customerDao.findByUsername(username);
		if(user!=null){
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

}
