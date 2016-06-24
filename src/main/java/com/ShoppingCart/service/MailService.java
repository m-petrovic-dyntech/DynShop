package com.ShoppingCart.service;

import java.util.List;

import com.ShoppingCart.entity.Customer;

public interface MailService {
	
	public void sendMail(String from, String to, String subject, String body, String templatePath);
	
	public void sendAlertMail(String alert);

	public void sendConfirmShoppingMail(Customer customer, String string, String string2, String string3, List<String> downloadLinks,
			String string4);

	public void sendTestMail(String from, String to, String string3, String string4);
	

}
