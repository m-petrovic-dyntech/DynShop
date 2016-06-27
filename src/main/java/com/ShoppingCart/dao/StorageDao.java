package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.entity.Delivery;

public interface StorageDao {

	public List<Delivery> getPendingOrders();

	public void changeDeliveryStatus(Delivery delivery);

	public Delivery getOrderById(int id);
	
}
