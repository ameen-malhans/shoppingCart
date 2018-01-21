package com.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","linesItems","attributeValues"})
public class Product implements java.io.Serializable {

	private Long idProduct;
	private Category category;
	private String productName;
	private String shortdescription;
	private String description;
	private String imageUrl;
	private BigDecimal price;
	private List<LineItem> linesItems = new ArrayList<LineItem>();

	public Product() {
	}

	public Product(String productName,String shortdescription,String description, BigDecimal price) {
		this.productName = productName;
		this.shortdescription = shortdescription;
		this.description = description;
		this.price = price;
	}

	public Product(Category category,String productName, String shortdescription,String description, BigDecimal price, List<LineItem> linesItems) {
		this.category = category;
		this.productName=productName;
		this.shortdescription = shortdescription;
		this.description = description;
		this.price = price;
		this.linesItems = linesItems;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idproduct", unique = true, nullable = false)
	public Long getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(Long idProduct) {
		this.idProduct = idProduct;
	}

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idcategory")
	public Category getCategory() {
		return this.category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "productname", nullable = false, length = 100)
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Column(name = "shortdescription", nullable = false, length = 50)
	public String getShortdescription() {
		return shortdescription;
	}

	public void setShortdescription(String shortdescription) {
		this.shortdescription = shortdescription;
	}

	@Column(name = "description", nullable = false, length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	@Column(name = "imageurl", nullable = false, length = 100)
	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	@JsonIgnore
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "product")
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}
	
	
	private Set<AttributeValue> attributeValues;

	@JsonIgnore
	@OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
	public Set<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Set<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}


}
