package com.ShoppingCart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@SuppressWarnings("unused")
@Entity
@Table(name="categories")
public class Category {
	@Id
	@SequenceGenerator(name="cat_gen", sequenceName="category_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="cat_gen")
	@Column(name="id")
	private Integer id;
	
	@Column(name="name")
	private String name;
	 
	@Column(name="enabled")
	private Boolean enabled;
	 
	@OneToMany(mappedBy="category")
	private List<Product> products;
	
	public Category()
	{
		this.products = new ArrayList<Product>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}
}
