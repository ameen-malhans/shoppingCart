package com.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "cart")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ShoppingCart implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long idCart;
	private User user;
	private BigDecimal subtotal;
	private List<LineItem> linesItems = new ArrayList<LineItem>();
	
	private Long userId;

	public ShoppingCart() {
	}

	@Transient
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public ShoppingCart(Long idCart, User customer, BigDecimal subtotal) {
		this.idCart = idCart;
		this.user = customer;
		this.subtotal = subtotal;
	}

	public ShoppingCart(Long idCart, User customer, BigDecimal subtotal, List<LineItem> linesItems) {
		this.idCart = idCart;
		this.user = customer;
		this.subtotal = subtotal;
		this.linesItems = linesItems;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcart", unique = true, nullable = false)
	public Long getIdCart() {
		return this.idCart;
	}

	public void setIdCart(Long idCart) {
		this.idCart = idCart;
	}

	@OneToOne
	@JoinColumn(name = "idcustomer")
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "subtotal", nullable = false, precision = 10)
	public BigDecimal getSubtotal() {
		return this.subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy = "cart")
	public List<LineItem> getLinesItems() {
		return this.linesItems;
	}

	public void setLinesItems(List<LineItem> linesItems) {
		this.linesItems = linesItems;
	}
	
	public BigDecimal calculateTotal(){
		BigDecimal total = BigDecimal.ZERO;
		for (LineItem lineItem : this.getLinesItems()) {
			BigDecimal quantity = new BigDecimal(lineItem.getQuantity());
			BigDecimal totalPrice = lineItem.getPrice().multiply(quantity);
			total = total.add(totalPrice);		
		}
		return total;
	}
}
