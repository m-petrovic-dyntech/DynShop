package com.ShoppingCart.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ShoppingCart.dao.StorageDao;
import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.entity.Delivery;
import com.ShoppingCart.entity.ShoppingCart;
import com.ShoppingCart.util.DeliveryStatus;

@Repository
public class StorageDaoImpl implements StorageDao {

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
	public List<Delivery> getPendingOrders() {

		return (List<Delivery>) getSession().createCriteria(Delivery.class)
				.add(Restrictions.eq("status", DeliveryStatus.PENDING)).list();
	}

	@Override
	@Transactional
	public void changeDeliveryStatus(Delivery delivery) {
		getSession().saveOrUpdate(delivery);
	}

	@Override
	@Transactional
	public Delivery getOrderById(int id) {

		return (Delivery) getSession().createCriteria(Delivery.class).add(Restrictions.eq("id", id)).uniqueResult();
	}

	@Override
	@Transactional
	public Customer getCustomerByDeliveryId(Integer id) {
		return null;
	}

	@Override
	@Transactional
	public ShoppingCart getCartByDeliveryId(Delivery delivery) {
		return (ShoppingCart) getSession().createCriteria(ShoppingCart.class).add(Restrictions.eq("delivery", delivery))
				.uniqueResult();
	}

}
