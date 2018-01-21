package com.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



@Entity
@NamedQuery(name = "AttributeValue.getProductDetailById", query = "select attval from AttributeValue attval inner join attval.attributeName attname inner join attname.features where attval.product.idProduct= ?1")
@Table(name = "attribute_value")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","attributeName"})
public class AttributeValue implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	
	private int id;
	private String attributeValues;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotEmpty
	@Column(name = "attribute_values")
	public String getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(String attributeValues) {
		this.attributeValues = attributeValues;
	}

	
	private Product product;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
	
	private AttributeName attributeName;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "attribute_id")
	public AttributeName getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(AttributeName attributeName) {
		this.attributeName = attributeName;
	}



	

	
}
