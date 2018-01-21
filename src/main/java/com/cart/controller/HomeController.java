package com.cart.controller;


import java.util.List;


import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cart.controller.exception.ProductNotFoundException;
import com.cart.dto.ItemDetail;
import com.cart.model.AttributeName;
import com.cart.model.AttributeValue;
import com.cart.model.Feature;
import com.cart.model.Product;
import com.cart.service.ProductService;


@RestController
public class HomeController {

	@Autowired
	ProductService itemService;
	
	@RequestMapping(value = "/catalog/products", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<List<Product>> getAllProducts() throws ProductNotFoundException {
		List<Product> products = itemService.getAllProducts();
		return new ResponseEntity<List<Product>> (products, HttpStatus.FOUND);
	}
	
	@RequestMapping(value = "/catalog/products/{idProduct}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<Product> getProductCatalogById(@PathVariable("idProduct") Long idProduct) throws ProductNotFoundException  {
		Product product = itemService.getProductCatalogById(idProduct);
		return new ResponseEntity<Product> (product, HttpStatus.FOUND);
	}

	@RequestMapping(value = "/detail/products/{idProduct}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<ItemDetail> getProductDetailById(@PathVariable("idProduct") Long idProduct) throws ProductNotFoundException  {
		Set<AttributeValue> attributeValues = itemService.getProductDetailById(idProduct);
		ItemDetail itemDetail = new ItemDetail();
		for (AttributeValue attributeValue : attributeValues) {
			AttributeName attributeName = attributeValue.getAttributeName();
			Feature feature = attributeName.getFeatures();
			itemDetail.getFeatures().add(feature);
		}
		return new ResponseEntity<ItemDetail> (itemDetail, HttpStatus.FOUND);
	}
	
}
