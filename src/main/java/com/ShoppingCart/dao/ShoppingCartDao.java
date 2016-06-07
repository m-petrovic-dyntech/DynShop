package com.ShoppingCart.dao;

import java.util.ArrayList;

import com.ShoppingCart.entity.Product;

public interface ShoppingCartDao {

	public void testWithHibernate();

	public ArrayList<Product> getProducts();

	public Product getProduct(int id); 
	
}
