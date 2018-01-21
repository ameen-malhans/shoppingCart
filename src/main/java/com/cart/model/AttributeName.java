package com.cart.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
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

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "attribute_name")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler","features"})
public class AttributeName implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	
	
	private int id;
	
	private String name;
	
	
	private String type;
	
	private Set<AttributeValue> attributeValues;
	
	private Feature features;
	
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
	@Column(name = "name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotEmpty
	@Column(name = "attribute_type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	@OneToMany(mappedBy="attributeName",fetch = FetchType.LAZY)
	public Set<AttributeValue> getAttributeValues() {
		return attributeValues;
	}

	public void setAttributeValues(Set<AttributeValue> attributeValues) {
		this.attributeValues = attributeValues;
	}
	

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "feature_id")
	public Feature getFeatures() {
		return features;
	}

	public void setFeatures(Feature features) {
		this.features = features;
	}

	
	
	
	

}
