package com.ShoppingCart.service;

import java.util.ArrayList;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;

public interface ShoppingCartService {
	
	public ArrayList<Product> getProducts();

	public Product getProduct(int id);
	
	public ArrayList<Category> getCategories();
	
	public ArrayList<Product> getProductsByCategory(int id);

}
