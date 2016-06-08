package com.ShoppingCart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="shoppingcartitem")
public class ShoppingCartItem {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	public int id;
	
	@Column(name="quantity")
	public int quantity;
	
	@Column(name="total")
	public Double total;
	
	@ManyToOne
	public Product product;
	
	@ManyToOne
	public ShoppingCart shoppingCart;
	
	public ShoppingCartItem() {}

	public ShoppingCartItem(int quantity, Double total, Product product) {
		this.quantity = quantity;
		this.total = total;
		this.product = product;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}
}
