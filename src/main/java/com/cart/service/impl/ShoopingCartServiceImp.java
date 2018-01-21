package com.cart.service.impl;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.model.Product;
import com.cart.model.LineItem;
import com.cart.model.ShoppingCart;
import com.cart.model.User;
import com.cart.repository.ProductRepository;
import com.cart.repository.LineItemRepository;
import com.cart.repository.ShoopingCartRepository;
import com.cart.repository.UserRepository;
import com.cart.service.ShoopingCartService;

@Service
@Transactional
public class ShoopingCartServiceImp implements ShoopingCartService {

	@Autowired
	ShoopingCartRepository cartDao;

	@Autowired
	ProductRepository productDao;
	
	@Autowired
	UserRepository customerDao;
	
	@Autowired
	LineItemRepository lineItemRepository;

	@Override
	public Long save(ShoppingCart cart) {
		return cartDao.save(cart).getIdCart();
	}

	@Override
	public void add(Long idCart, Long idProduct, Integer quantity) {
		Product product = productDao.findOne(idProduct);
		ShoppingCart cart = cartDao.findOne(idCart);
		LineItem lineItem = new LineItem();
		lineItem.setQuantity(quantity);
		lineItem.setPrice(product.getPrice());
		lineItem.setCart(cart);
		lineItem.setProduct(product);
		lineItemRepository.save(lineItem);
	}

	@Override
	public void removeItemFromCart(Long idCart,Long idProduct) {
		ShoppingCart cart =cartDao.findOne(idCart);
		ListIterator<LineItem> iter = cart.getLinesItems().listIterator();
		while(iter.hasNext()){
			LineItem lineItem = iter.next();
		    if(lineItem.getProduct().getIdProduct().equals(idProduct)){
		    	iter.remove();
		    	lineItemRepository.delete(lineItem);
		    }
		}
	}

	@Override
	public ShoppingCart getShoopingCartByUser(User user) {
		return cartDao.getShoopingCartByUser(user);
	}

}
