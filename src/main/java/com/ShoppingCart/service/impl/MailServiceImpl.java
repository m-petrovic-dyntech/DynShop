package com.ShoppingCart.service.impl;

import java.io.StringWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender  mailSender;
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
		velocityContext.put("downloadLink", body);

		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		message.setText(stringWriter.toString());

		mailSender.send(message);

	}

	public void sendConfirmShoppingMail(Customer customer, String from, String to, String subject,
			List<String> cartItems, String templatePath) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);

		Template template = velocityEngine.getTemplate("./templates/" + templatePath);

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("firstName", customer.getFirstName());
		velocityContext.put("lastName", customer.getLastName());

		if (!cartItems.isEmpty())
			velocityContext.put("downloadLinks", cartItems);

		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		message.setText(stringWriter.toString());

		mailSender.send(message);

	}

	public void sendTestMail(String from, String to, String dear, String content) {

		
		  MimeMessage message = mailSender.createMimeMessage();
		  Template template = velocityEngine.getTemplate("./templates/confirmShoppingTemplate.vm");
		  
		  VelocityContext velocityContext = new VelocityContext();
		  velocityContext.put("firstName","Nata");
		  velocityContext.put("lastName", "Kitanoska");
		 	
		  try{
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
				
			helper.setFrom(from);
			helper.setTo(to);
			helper.setSubject("Test");
			
			StringWriter stringWriter = new StringWriter();
			template.merge(velocityContext, stringWriter);
			helper.setText(stringWriter.toString());	
			//stringWriter.close();
			FileSystemResource file = new FileSystemResource("C:\\mapping.jpg");
			helper.addAttachment(file.getFilename(), file);

		     }
		  
		   catch (MessagingException e) {
			throw new MailParseException(e);
		   }
		  
		  /*
		  try {
			message.setText(stringWriter.toString());
			} catch (MessagingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
		  }*/
		     mailSender.send(message); 
		    
	}
	

	@Override
	public void sendAlertMail(String alert) {
		// TODO Auto-generated method stub
		
	}

	
}
