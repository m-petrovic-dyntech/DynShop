package com.ShoppingCart.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
import com.ShoppingCart.entity.Delivery;
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

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<Double> getAllCartsPurcashedByMonth(int month) {
		
		GregorianCalendar gc = new GregorianCalendar(2016, month-1, 1);
	    Date monthFirstDate = new java.util.Date(gc.getTime().getTime());
	    
	    gc.setTime(monthFirstDate);
	    gc.add(Calendar.MONTH, 1);  
        gc.set(Calendar.DAY_OF_MONTH, 1);  
        gc.add(Calendar.DATE, -1);  
        Date monthLastDate = gc.getTime();  

		Criteria results = getSession().createCriteria(ShoppingCart.class);
		results.add(Restrictions.between("shoppingDate", monthFirstDate, monthLastDate));
		List<ShoppingCart> carts = results.list();

		List<Double> totals = new ArrayList<Double>();
		for (ShoppingCart shoppingCart : carts) {
			totals.add(shoppingCart.getTotalCost());
		}
		
		return totals;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ShoppingCart> getPendingCarts() {
		
		return (List<ShoppingCart>) getSession().createCriteria(ShoppingCart.class)
				.add(Restrictions.eq("status", "pending")).list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ShoppingCart> getPendingCarts(int pageNum, int pageSize) {
		
		Criteria results = getSession().createCriteria(ShoppingCart.class);
		results.add(Restrictions.like("status", "pending"));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@Transactional
	@Override
	public ShoppingCart getCartById(Integer id) {
		ShoppingCart cart = (ShoppingCart) getSession().createCriteria(ShoppingCart.class).add(Restrictions.eq("id", id))
				.uniqueResult();
		return cart;
	}

	@Transactional
	@Override
	public void editCart(ShoppingCart cart) {
		getSession().saveOrUpdate(cart);
	}

	@Transactional
	@Override
	public int getCountPandingCarts() {
		
		Long result = (Long) getSession().createCriteria(ShoppingCart.class).add(Restrictions.eq("status", "pending")).setProjection(Projections.rowCount())
				.uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Transactional
	@Override
	public void addDelivery(Delivery delivery) {
		getSession().save(delivery);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ShoppingCart> getDeliveryCarts() {
		return (List<ShoppingCart>) getSession().createCriteria(ShoppingCart.class)
				.add(Restrictions.not(Restrictions.eq("status", "pending")))
				.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ShoppingCart> getDeliveryCarts(int pageNum, int pageSize) {
		//paginacija za sve statuse osim pendinga
		Criteria results = getSession().createCriteria(ShoppingCart.class);
		results.add(Restrictions.not(Restrictions.eq("status", "pending")));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ShoppingCart> getDeliveryCarts(String status) {
		return (List<ShoppingCart>) getSession().createCriteria(ShoppingCart.class)
		.add(Restrictions.eq("status", status)).list();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	@Override
	public List<ShoppingCart> getDeliveryCarts(String status, int pageNum, int pageSize) {
		
		Criteria results = getSession().createCriteria(ShoppingCart.class);
		results.add(Restrictions.eq("status", status));
		results.setFirstResult((pageNum - 1) * pageSize);
		results.setMaxResults(pageSize);
		return results.list();
	}

	@Transactional
	@Override
	public int getCountDeliveryCarts() {
		Number result = (Number) getSession().createCriteria(ShoppingCart.class).add(Restrictions.not(Restrictions.eq("status", "pending"))).setProjection(Projections.rowCount())
				.uniqueResult();
		return Integer.parseInt(result.toString());
	}

	@Transactional
	@Override
	public int getCountDeliveryCarts(String status) {
		Number result = (Number) getSession().createCriteria(ShoppingCart.class).add(Restrictions.eq("status", status)).setProjection(Projections.rowCount())
				.uniqueResult();
		return Integer.parseInt(result.toString());
	}
	

}
