package com.cart.model;
import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "features")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Feature implements Serializable{

	private static final long serialVersionUID = 1L;
	
	
	private int id;
	private String name;
	private String type;
	private Set<AttributeName> attributeNames;

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
	@Size(min = 2, max = 50)
	@Column (name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty
	@Size(min = 2, max = 50)
	@Column (name = "feature_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	

	@OneToMany(mappedBy = "features",fetch = FetchType.EAGER,cascade = CascadeType.PERSIST, orphanRemoval=true )
	public Set<AttributeName> getAttributeNames() {
		return attributeNames;
	}

	public void setAttributeNames(Set<AttributeName> attributeNames) {
		this.attributeNames = attributeNames;
	}
	
}
