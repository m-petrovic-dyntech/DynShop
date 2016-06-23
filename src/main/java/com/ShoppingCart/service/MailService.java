package com.ShoppingCart.service;

import java.util.List;

public interface MailService {
	
	public void sendMail(String from, String to, String subject, String body, String templatePath);
	
	public void sendAlertMail(String alert);

	public void sendConfirmShoppingMail(Integer custoimerId, String string, String string2, String string3, List<String> downloadLinks,
			String string4);
	

}
