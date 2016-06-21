package com.ShoppingCart.service.impl;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.ShoppingCart.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private MailSender mailSender;
	@Autowired
	private SimpleMailMessage alertMailMessage;
	@Autowired
	private VelocityEngine velocityEngine;
	
	
	

	public void sendMail(String from, String to, String subject, String body, String templatePath) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		
		Template template = velocityEngine.getTemplate("./templates/" + templatePath);
		
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("firstName", "Natalija");
		velocityContext.put("lastName", "Kitanoska");
		velocityContext.put("location", "Palilula");
		
		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		message.setText(stringWriter.toString());
		
		mailSender.send(message);
		
	}

	public void sendAlertMail(String alert) {

		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);

	}

}
