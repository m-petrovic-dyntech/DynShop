package com.ShoppingCart.dao;

import java.util.ArrayList;
import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;

public interface ShoppingCartDao {

	public ArrayList<Product> getProducts();

	public Product getProduct(int id); 
	
	public ArrayList<Category> getCategories();
	
	public Category getCategoryById(int id);
	
	public void saveCart(ShoppingCart cart);
	
	public List<Product> getProductsByCategory(Category category);
 }
