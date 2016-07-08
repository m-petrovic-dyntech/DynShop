package com.ShoppingCart.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.ShoppingCart.dao.UserDao;
import com.ShoppingCart.entity.UserAccount;

public class UserDaoImpl implements UserDao{
	
	private SessionFactory sessionFactory;
	
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

	@Override
	@Transactional
	public UserAccount getUserAccByUsername(String username) {
		 UserAccount userAcc = (UserAccount) getSession()
				 .createCriteria(UserAccount.class)
				 .add(Restrictions.eq("username", username))
				 .uniqueResult();
		 return userAcc;
	}

	@Override
	@Transactional
	public UserAccount findByEmailAndPassword(String username, String email) {
		
		UserAccount userAcc =  (UserAccount) getSession()
				.createCriteria(UserAccount.class)
				.add(Restrictions.or(Restrictions.eq("username", username), Restrictions.eq("email", email)))
				.uniqueResult();
		
		return userAcc;
	}

	@Override
	@Transactional
	public void saveUser(UserAccount userAccount) {
		getSession().saveOrUpdate(userAccount);
	}

}
