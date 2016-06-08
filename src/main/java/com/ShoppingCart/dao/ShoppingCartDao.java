package com.ShoppingCart.dao;

import java.util.ArrayList;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;

public interface ShoppingCartDao {

	public ArrayList<Product> getProducts();

	public Product getProduct(int id); 
	
	public ArrayList<Category> getCategories();
	
	public ArrayList<Product> getProductsByCategory(int id);
 }
