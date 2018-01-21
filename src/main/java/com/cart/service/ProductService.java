package com.cart.service;

import java.util.List;
import java.util.Set;

import com.cart.controller.exception.ProductNotFoundException;
import com.cart.model.AttributeValue;
import com.cart.model.Product;



public interface ProductService {

	Product getProductCatalogById(Long idProduct) throws ProductNotFoundException;
	List<Product> getAllProducts() throws ProductNotFoundException;
	Set<AttributeValue> getProductDetailById(Long idProduct) throws ProductNotFoundException;
	
	
}
