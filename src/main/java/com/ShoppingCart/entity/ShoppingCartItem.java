package com.ShoppingCart.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="shoppingcartitems")
public class ShoppingCartItem {
	@Id
	@SequenceGenerator(name="shoppingcartitem_gen", sequenceName="shoppingcartitem_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shoppingcartitem_gen")
	@Column(name="id")
	public Integer id;
	
	@Column(name="quantity")
	public Integer quantity;
	
	@Column(name="total")
	public Double total;
	
	@Column(name="enabled")
	private Boolean enabled;
	
	@ManyToOne
	@JoinColumn(name = "PRODUCT_ID", referencedColumnName="ID")
	public Product product;
	
	@ManyToOne
	@JoinColumn(name = "SHOPPINGCART_ID", referencedColumnName="ID")
	public ShoppingCart shoppingCart;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
}
