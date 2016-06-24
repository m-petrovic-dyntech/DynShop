package com.ShoppingCart.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="delivery")
public class Delivery {

	@Id
	@SequenceGenerator(name="delivery_gen", sequenceName="delivery_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="delivery_gen")
	@Column(name="id")
	private Integer id;
	
	@OneToMany(mappedBy="delivery")
	private List<Customer> customers;
	
	@OneToMany(mappedBy="delivery")
	private List<Product> products;
	
	@Column(name="status")
	private String status;
	
	public Delivery()
	{
		this.products = new ArrayList<Product>();
		this.customers = new ArrayList<Customer>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	public void setCustomers(List<Customer> customers) {
		this.customers = customers;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
