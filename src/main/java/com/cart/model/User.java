package com.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","cartses","orderses","cart"})
public class User implements java.io.Serializable {

	private Long idCustomer;
	private String lastName;
	private String firstName;
	private String username;
	private String password;
	private ShoppingCart cart;

	public User() {
	}

	public User(Long idCustomer, String lastName, String firstName, String username, String password) {
		this.idCustomer = idCustomer;
		this.lastName = lastName;
		this.firstName = firstName;
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idcustomer", unique = true, nullable = false)
	public Long getIdcustomer() {
		return this.idCustomer;
	}

	public void setIdcustomer(Long idcustomer) {
		this.idCustomer = idcustomer;
	}

	@Column(name = "last_name", nullable = false, length = 50)
	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "first_name", nullable = false, length = 50)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@NotEmpty
	@Length(max = 40)
	@Pattern(regexp=".+@.+\\..+")
	@Email
	@Column(name = "username", unique = true, nullable = false, length = 50)
	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", nullable = false, length = 256)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne(mappedBy = "user")
	@Cascade(CascadeType.SAVE_UPDATE)
	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}



}
