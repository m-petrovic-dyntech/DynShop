package com.ShoppingCart.service;

import java.util.ArrayList;
import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Product;

public interface ShoppingCartService {
	
	public List<Product> getProducts(Integer categoryId);

	public Product getProduct(int id);
	
	public ArrayList<Category> getCategories();
	
	public Category getCategoryById(Integer id);

}
