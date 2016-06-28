package com.ShoppingCart.dto;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Delivery;
import com.ShoppingCart.entity.ShoppingCart;

public class JtoDelivery {

	private Delivery delivery;
	private Customer customer;
	private ShoppingCart cart;
	
	public JtoDelivery()
	{
		
	}

	public Delivery getDelivery() {
		return delivery;
	}

	public void setDelivery(Delivery delivery) {
		this.delivery = delivery;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public ShoppingCart getCart() {
		return cart;
	}

	public void setCart(ShoppingCart cart) {
		this.cart = cart;
	}

	
}
