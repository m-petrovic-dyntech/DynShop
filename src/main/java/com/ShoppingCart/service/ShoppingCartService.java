package com.ShoppingCart.service;

import java.util.ArrayList;

import com.ShoppingCart.entity.Product;

public interface ShoppingCartService {
	
	public void testHibernate();
	
	public ArrayList<Product> getProducts();

	public Product getProduct(int id);

}
