package com.ShoppingCart.service;

import java.util.List;

import com.ShoppingCart.entity.Delivery;

public interface StorageManagementService {

	public List<Delivery> getPendingOrders();
	
	public void changeDeliveryStatus(Delivery delivery);

	public Delivery getOrderById(int id);
}
