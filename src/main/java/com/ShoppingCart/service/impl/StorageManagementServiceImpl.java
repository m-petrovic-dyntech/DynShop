package com.ShoppingCart.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ShoppingCart.dao.StorageDao;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Delivery;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.service.StorageManagementService;

@Service
public class StorageManagementServiceImpl implements StorageManagementService{

	@Autowired
	private StorageDao storageDao;
	
	@Override
	public List<Delivery> getPendingOrders() {
		return storageDao.getPendingOrders();
	}

	@Override
	public void changeDeliveryStatus(Delivery delivery) {
		storageDao.changeDeliveryStatus(delivery);
	}

	@Override
	public Delivery getOrderById(int id) {
		return storageDao.getOrderById(id);
	}

	@Override
	public Customer getCustomerByDeliveryId(Integer id) {
		return storageDao.getCustomerByDeliveryId(id);
	}

	@Override
	public ShoppingCart getCartByDeliveryId(Integer id) {
		return storageDao.getCartByDeliveryId(id);
	}

}
