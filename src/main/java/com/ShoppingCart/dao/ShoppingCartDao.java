package com.ShoppingCart.dao;

import java.util.ArrayList;
import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

public interface ShoppingCartDao {

	public ArrayList<Product> getProducts();

	public Product getProduct(int id); 
	
	public ArrayList<Category> getCategories();
	
	public Category getCategoryById(int id);
	
	public void saveCart(ShoppingCart cart);
	
	public List<Product> getProductsByCategory(Category category);

	public List<ShoppingCart> getCartsByCustomer(Customer customer);
	
	public List<ShoppingCart> getAllCarts();

	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart);
	
	public void editCategory(Category category);
	
	public void addCategory(Category category);

	public void deleteCategory(Category category);
	
	public void editProduct(Product product);

	public void deleteProduct(Product product);
	
	public void addProduct(Product product);
 }
