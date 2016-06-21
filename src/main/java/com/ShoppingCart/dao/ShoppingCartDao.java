package com.ShoppingCart.dao;

import java.util.List;

import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

public interface ShoppingCartDao {

	public List<Product> getProducts();

	public List<Product> getProducts(int pageNum, int pageSize);

	public Product getProductById(int id);

	public List<Category> getCategories();

	public List<Category> getCategories(Integer page, Integer size);

	public Category getCategoryById(int id);

	public void saveCart(ShoppingCart cart);

	public List<Product> getProductsByCategory(Category category);

	public List<Product> getProductsByCategory(Category category, int pageNum, int pageSize);

	public List<ShoppingCart> getCartsByCustomer(Customer customer);

	public List<ShoppingCart> getCartsByCustomer(Customer customer, int pageNum, int pageSize);

	public List<ShoppingCart> getAllCarts();

	public List<ShoppingCart> getAllCarts(int pageNum, int pageSize);

	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart);

	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart, int pageNum, int pageSize);

	public void editCategory(Category category);

	public void addCategory(Category category);

	public void editProduct(Product product);

	public void addProduct(Product product);

	public List<Category> getEnabledCategories();

	public List<Category> getEnabledCategories(int pageNum, int pageSize);

	public List<Category> getDisabledCategories();

	public List<Category> getDisabledCategories(int pageNum, int pageSize);

	public List<Product> getEnabledProducts();

	public List<Product> getEnabledProducts(int pageNum, int pageSize);

	public List<Product> getEnabledProductsByCategory(Category category);

	public List<Product> getEnabledProductsByCategory(Category category, int pageNum, int pageSize);

	public List<Product> getDisabledProducts();

	public List<Product> getDisabledProducts(int pageNum, int pageSize);
	
	public int getCountCategories();

	public int getCountProducts();
	
	public int getCountProductsInCategory(int categoryId);
	
	public int getCountCarts();
	
	public int getCountCartsInCustomer(Customer customer);
	
	public int getCountItemsInCart(int id);


}
