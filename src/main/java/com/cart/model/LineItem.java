package com.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "line_item")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cart"})
public class LineItem implements java.io.Serializable {

	private Long idlinesItem;
	private ShoppingCart cart;
	private Product product;
	private Integer quantity;
	private BigDecimal price;

	public LineItem() {
	}

	public LineItem(ShoppingCart cart, Product product, Integer quantity, BigDecimal price) {
		this.cart = cart;
		this.product = product;
		this.quantity = quantity;
		this.price = price;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idlines_item", unique = true, nullable = false)
	public Long getIdlinesItem() {
		return this.idlinesItem;
	}

	public void setIdlinesItem(Long idlinesItem) {
		this.idlinesItem = idlinesItem;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idcart")
	public ShoppingCart getCart() {
		return this.cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproduct")
	public Product getProduct() {
		return this.product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}


	@Column(name = "quantity", nullable = false)
	public Integer getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	@Column(name = "price", nullable = false, precision = 10)
	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	

}
