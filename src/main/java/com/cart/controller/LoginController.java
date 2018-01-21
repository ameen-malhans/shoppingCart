package com.cart.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	com.cart.service.UserService userService;

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<com.cart.model.User> login(@RequestBody com.cart.model.User user,
			HttpServletRequest request)
			throws NoSuchAlgorithmException, com.cart.controller.exception.AuthenticationFailedException {
		if (user == null) {
			HttpHeaders header = new HttpHeaders();
			return new ResponseEntity<com.cart.model.User>(header, HttpStatus.BAD_REQUEST);
		}
		user = userService.authentication(user.getUsername(), user.getPassword());
		return new ResponseEntity<com.cart.model.User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> addCustomer(@RequestBody com.cart.model.User user,
			HttpServletRequest request) throws URISyntaxException, NoSuchAlgorithmException {
		if (userService.isUserExist(user.getUsername())) {
			HttpHeaders header = new HttpHeaders();
			return new ResponseEntity<Void>(header, HttpStatus.CONFLICT);
		}
		Long id = userService.addUser(user);
		HttpHeaders header = new HttpHeaders();
		header.setLocation(new URI(request.getRequestURL() + "/users/" + id.toString() + "/carts"));
		return new ResponseEntity<Void>(header, HttpStatus.CREATED);
	}

}
