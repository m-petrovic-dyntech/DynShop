package com.ShoppingCart.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="shoppingcarts")
public class ShoppingCart {
	@Id
	@SequenceGenerator(name="shoppingcart_gen", sequenceName="shoppingcart_seq", allocationSize=1, initialValue=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="shoppingcart_gen")
	@Column(name="id")
	private Integer id;
	
	@Column(name="totalCost")
	private Double totalCost;
	
	@OneToMany(mappedBy="shoppingCart", cascade=CascadeType.ALL)
	private List<ShoppingCartItem> items;
	
	@Column(name="shoppingDate")
	@Temporal(TemporalType.DATE)
	private Date shoppingDate;
	
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID", referencedColumnName="ID")
	private Customer customer;

	public ShoppingCart()
	{
		this.totalCost = 0.0;
		this.items = new ArrayList<ShoppingCartItem>();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
	
	public ShoppingCartItem findItemByProductId(int productId) {
		for (ShoppingCartItem shoppingCartItem : this.items) {
			if(shoppingCartItem.getProduct().getId() == productId)
				return shoppingCartItem;
		}
		
		return null;
	}

	public Date getShoppingDate() {
		return shoppingDate;
	}

	public void setShoppingDate(Date shoppingDate) {
		this.shoppingDate = shoppingDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "ShoppingCart [id=" + id + ", totalCost=" + totalCost + ", items=" + items + ", shoppingDate="
				+ shoppingDate + "]";
	}
}
