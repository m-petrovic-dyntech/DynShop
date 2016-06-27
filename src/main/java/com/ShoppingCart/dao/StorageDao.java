package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Delivery;
import com.ShoppingCart.entity.ShoppingCart;

public interface StorageDao {

	public List<Delivery> getPendingOrders();

	public void changeDeliveryStatus(Delivery delivery);

	public Delivery getOrderById(int id);

	public Customer getCustomerByDeliveryId(Integer id);

	public ShoppingCart getCartByDeliveryId(Integer id);
	
}
