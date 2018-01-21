package com.cart.controller;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cart.controller.exception.AuthenticationFailedException;
import com.cart.model.ShoppingCart;
import com.cart.model.User;
import com.cart.service.ShoopingCartService;
import com.cart.service.UserService;
import com.cart.util.ShoppingCartUtils;

@RestController
public class ShoppingCartController {

	@Autowired
	ShoopingCartService cartService;
	
	@Autowired
	UserService userService;

	
	@RequestMapping(value = "/users/{idUser}/carts", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<Void> create(@PathVariable("idUser") Long idUser,@RequestParam("idProduct") Long idProduct,
			@RequestParam("quantity") Integer quantity ,HttpServletRequest request) throws AuthenticationFailedException {
		//Check if user exists
		User user = userService.getUser(idUser);
		
		//If cart exits add in existing cart
		Long idCart =null;
		ShoppingCart cartExists = cartService.getShoopingCartByUser(user);
		if (cartExists == null) {
			// Create a dummy Cart
			ShoppingCart cart = new ShoppingCart();
			cart.setUser(user);
			cart.setSubtotal(BigDecimal.ZERO);
			idCart = cartService.save(cart);
		} else {
			idCart = cartExists.getIdCart();
		}
		
		//Add product to the cart
		cartService.add(idCart, idProduct, quantity);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	

	@RequestMapping(value = "/users/{idUser}/carts", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ShoppingCart> getAll(@PathVariable("idUser") Long idUser, HttpServletRequest request)  throws URISyntaxException,AuthenticationFailedException{
		//Check if user exists
		User user = userService.getUser(idUser);
		
		ShoppingCart cartExists = cartService.getShoopingCartByUser(user);
		if(cartExists == null || !ShoppingCartUtils.isLineItemsExists(cartExists)){
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI(ShoppingCartUtils.getHostName(request) + "/catalog/products"));
			return new ResponseEntity<ShoppingCart> (header, HttpStatus.NOT_FOUND);
		}
		cartExists.setSubtotal(cartExists.calculateTotal());
		return new ResponseEntity<ShoppingCart> (cartExists, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/users/{idUser}/carts/{idProduct}", method = RequestMethod.DELETE)
	public @ResponseBody ResponseEntity<Void> removeItemFromCart(@PathVariable("idUser") Long idUser,@PathVariable("idProduct") Long idProduct, HttpServletRequest request)  throws URISyntaxException, AuthenticationFailedException {
		//Check if user exists
		User user = userService.getUser(idUser);
		
		ShoppingCart cartExists = cartService.getShoopingCartByUser(user);
		if(cartExists == null || !ShoppingCartUtils.isLineItemsExists(cartExists)){
			HttpHeaders header = new HttpHeaders();
			header.setLocation(new URI(request.getRequestURL() + "/catalog/products"));
			return new ResponseEntity<Void> (HttpStatus.NOT_FOUND);
		}
		
		cartService.removeItemFromCart(cartExists.getIdCart(),idProduct);
		return new ResponseEntity<Void> (HttpStatus.CREATED);
	}
	
}
