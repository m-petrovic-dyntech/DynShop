package com.ShoppingCart.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

//@Component
//@Scope("session")
@Entity
@Table(name="shoppingcart")
public class ShoppingCart {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name="id")
	private ShoppingCartItem shoppingCartItem;
	
	@Column(name="totalCost")
	private Double totalCost;
	
	@OneToMany(mappedBy="shoppingCart")
	private List<ShoppingCartItem> items;
	
	@Column(name="shoppingDate")
	@Temporal(TemporalType.DATE)
	private Date shoppingDate;
	
	public ShoppingCart() {}

	public ShoppingCart(ShoppingCartItem shoppingCartItem, Double totalCost, List<ShoppingCartItem> items, Date date) {
		this.shoppingCartItem = shoppingCartItem;
		this.totalCost = totalCost;
		this.items = items;
		this.shoppingDate = date;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShoppingCartItem getShoppingCartItem() {
		return shoppingCartItem;
	}

	public void setShoppingCartItem(ShoppingCartItem shoppingCartItem) {
		this.shoppingCartItem = shoppingCartItem;
	}

	public Double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(Double totalCost) {
		this.totalCost = totalCost;
	}

	public List<ShoppingCartItem> getItems() {
		return items;
	}

	public void setItems(List<ShoppingCartItem> items) {
		this.items = items;
	}
	
	public Date getDate() {
		return shoppingDate;
	}

	public void setDate(Date date) {
		this.shoppingDate = date;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", shoppingCartItem=" + shoppingCartItem + ", totalCost=" + totalCost
				+ ", items=" + items + "]";
	}
	
	
}
