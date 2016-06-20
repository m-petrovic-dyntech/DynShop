package com.ShoppingCart.service;

public interface MailService {
	
	public void sendMail(String from, String to, String subject, String body);
	
	public void sendAlertMail(String alert);
	

}
