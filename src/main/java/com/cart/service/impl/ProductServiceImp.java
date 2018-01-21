package com.cart.service.impl;
import java.util.List;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cart.controller.exception.ProductNotFoundException;
import com.cart.model.AttributeValue;
import com.cart.model.Product;
import com.cart.repository.AttributeValueRepository;
import com.cart.repository.ProductRepository;
import com.cart.service.ProductService;

@Service
@Transactional
public class ProductServiceImp implements ProductService {

	@Autowired
	ProductRepository productDao;
	
	@Autowired
	AttributeValueRepository attributeValueRepository;
	

	@Override
	public Product getProductCatalogById(Long idProduct) throws ProductNotFoundException {
		Product product = productDao.findOne(idProduct);
		if (product != null)
			return product;
		else
			throw new ProductNotFoundException();
	}


	@Override
	public List<Product> getAllProducts() throws ProductNotFoundException {
		List<Product> products = productDao.findAll();
		if (products.isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return products;
	}

	@Override
	public Set<AttributeValue> getProductDetailById(Long idProduct) throws ProductNotFoundException {
		Set<AttributeValue> attributeValues = attributeValueRepository.getProductDetailById(idProduct);
		return attributeValues;
	}

}
