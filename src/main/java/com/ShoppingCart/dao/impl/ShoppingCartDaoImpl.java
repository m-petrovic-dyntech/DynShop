package com.ShoppingCart.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.ShoppingCartDao;
import com.ShoppingCart.entity.EntityTest;

@Repository
public class ShoppingCartDaoImpl implements ShoppingCartDao {

	private final Log logger = LogFactory.getLog(getClass());
		
	private SessionFactory sessionFactory;
	 
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
	  this.sessionFactory = sessionFactory;
	}
	 
	public Session getSessionFactory() {
	  return sessionFactory.getCurrentSession();
	}

	@Transactional
	@Override
	public void testWithHibernate() {
		
		Session session = sessionFactory.getCurrentSession();
		
		session.beginTransaction();
		EntityTest instance = new EntityTest();

		instance.setInstanceId(999);
		instance.setRebootDate(null);
		instance.setServiceName("TestTest");
		instance.setInstanceName("x55555xxx");

		session.save(instance); 
				
	}

}
