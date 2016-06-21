package com.ShoppingCart.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.Category;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Product;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.entity.ShoppingCartItem;

@Repository

public class ShoppingCartDaoImpl implements ShoppingCartDao {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());

	private SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	private Session session;

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getProducts() {
		List<Product> results = (List<Product>) getSession().createCriteria(Product.class).list();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getProducts(int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Product.class);
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@Override
	@Transactional
	public Product getProductById(int id) {
		Product product = (Product) getSession().createCriteria(Product.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		return product;

	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getCategories() {
		List<Category> results = (List<Category>) getSession().createCriteria(Category.class).list();
		return results;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getCategories(Integer page, Integer size) {

		Criteria results = getSession().createCriteria(Category.class);
		results.setFirstResult((page - 1) * size);
		results.setMaxResults(size);
		return results.list();

	}

	@Override
	@Transactional
	public Category getCategoryById(int id) {
		Category category = (Category) getSession().createCriteria(Category.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		return category;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getProductsByCategory(Category category) {
		return (List<Product>) getSession().createCriteria(Product.class).add(Restrictions.eq("category", category))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getProductsByCategory(Category category, int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Product.class);
		results.add(Restrictions.eq("category", category));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@Override
	@Transactional
	public void saveCart(ShoppingCart cart) {
		cart.setShoppingDate(new java.util.Date());
		getSession().saveOrUpdate(cart);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ShoppingCart> getCartsByCustomer(Customer customer) {
		List<ShoppingCart> carts = (List<ShoppingCart>) getSession().createCriteria(ShoppingCart.class)
				.add(Restrictions.eq("customer", customer)).list();
		return carts;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ShoppingCart> getCartsByCustomer(Customer customer, int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(ShoppingCart.class);
		results.add(Restrictions.eq("customer", customer));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ShoppingCart> getAllCarts() {
		List<ShoppingCart> carts = (List<ShoppingCart>) getSession().createCriteria(ShoppingCart.class).list();
		return carts;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<ShoppingCart> getAllCarts(int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(ShoppingCart.class);
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart) {
		return (List<ShoppingCartItem>) getSession().createCriteria(ShoppingCartItem.class)
				.add(Restrictions.eq("shoppingCart", cart)).list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public List<ShoppingCartItem> getItemsByCart(ShoppingCart cart, int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(ShoppingCartItem.class);
		results.add(Restrictions.eq("shoppingCart", cart));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@Override
	@Transactional
	public void editCategory(Category category) {
		getSession().saveOrUpdate(category);

	}

	@Override
	@Transactional
	public void editProduct(Product product) {
		getSession().saveOrUpdate(product);

	}

	@Override
	@Transactional
	public void addCategory(Category category) {
		getSession().save(category);
	}

	@Override
	@Transactional
	public void addProduct(Product product) {
		getSession().save(product);
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getEnabledCategories() {
		return (List<Category>) getSession().createCriteria(Category.class)
				.add(Restrictions.eq("enabled", Boolean.TRUE)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getEnabledCategories(int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Category.class);
		results.add(Restrictions.eq("enabled", Boolean.TRUE));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getDisabledCategories() {
		return (List<Category>) getSession().createCriteria(Category.class)
				.add(Restrictions.eq("enabled", Boolean.FALSE)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Category> getDisabledCategories(int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Category.class);
		results.add(Restrictions.eq("enabled", Boolean.FALSE));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getEnabledProducts() {
		return (List<Product>) getSession().createCriteria(Product.class).add(Restrictions.eq("enabled", Boolean.TRUE))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getEnabledProducts(int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Product.class);
		results.add(Restrictions.eq("enabled", Boolean.TRUE));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getEnabledProductsByCategory(Category category) {
		return (List<Product>) getSession().createCriteria(Product.class).add(Restrictions.eq("enabled", Boolean.TRUE))
				.add(Restrictions.eq("category", category)).list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getEnabledProductsByCategory(Category category, int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Product.class);
		results.add(Restrictions.eq("enabled", Boolean.TRUE));
		results.add(Restrictions.eq("category", category));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getDisabledProducts() {
		return (List<Product>) getSession().createCriteria(Product.class).add(Restrictions.eq("enabled", Boolean.FALSE))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Product> getDisabledProducts(int pageNum, int pageSize) {

		Criteria results = getSession().createCriteria(Product.class);
		results.add(Restrictions.eq("enabled", Boolean.FALSE));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@Override
	@Transactional
	public int getCountCategories()
	{

		Long result = (Long) getSession().createCriteria(Category.class).setProjection(Projections.rowCount())
				.uniqueResult();
		return Integer.parseInt(result.toString());
		// drugi takodje ispravan nacin
		// Number result = (Number) getSession().createSQLQuery("select count(*)
		// from categories").uniqueResult();
		// return Integer.parseInt(result.toString());
	}

	@Override
	@Transactional
	public int getCountProducts() {
		Number result = (Number) getSession().createSQLQuery("select count(*) from products").uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Override
	@Transactional
	public int getCountProductsInCategory(int categoryId) {

		String hql = "select count(*) from products where category_id = :id";
		Number result = (Number) getSession().createSQLQuery(hql).setInteger("id", categoryId).uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Override
	@Transactional
	public int getCountCarts() {
		Number result = (Number) getSession().createSQLQuery("select count(*) from shoppingcarts").uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Override
	@Transactional
	public int getCountCartsInCustomer(Customer customer) {
		
		String hql = "select count(*) from shoppingcarts where customer_id = :id";
		Number result = (Number) getSession().createSQLQuery(hql)
				.setInteger("id", customer.getId()).uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Override
	@Transactional
	public int getCountItemsInCart(int id) {
		
		String hql = "select count(*) from shoppingcartitems where shoppingcart_id = :id";
		Number result = (Number) getSession().createSQLQuery(hql)
				.setInteger("id", id).uniqueResult();
		return Integer.parseInt(result.toString());
	}

}
