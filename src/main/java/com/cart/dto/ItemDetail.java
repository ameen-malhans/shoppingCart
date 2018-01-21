package com.cart.dto;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.cart.model.Category;
import com.cart.model.Feature;

public class ItemDetail implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Set<Feature> features = new HashSet();

	public Set<Feature> getFeatures() {
		return features;
	}

	public void setFeatures(Set<Feature> features) {
		this.features = features;
	}

	
	
	
	
	
}