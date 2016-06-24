package com.ShoppingCart.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.ShoppingCart.entity.Customer;
import com.ShoppingCart.service.MailService;

@Service
public class MailServiceImpl implements MailService {

	@Autowired
	private JavaMailSender mailSender;
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

	public void sendAlertMail(String alert) {

		SimpleMailMessage mailMessage = new SimpleMailMessage(alertMailMessage);
		mailMessage.setText(alert);
		mailSender.send(mailMessage);

	}

	public void sendPaymentOrder(Customer customer, String from, String to, String subject, String templatePath)
			throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);

		Multipart multipart = new MimeMultipart();
		// MimeBodyPart messageBodyPart = new MimeBodyPart();
		// String message1 = "probamo nesto";
		// messageBodyPart.setText(message1, "utf-8", "html");
		// multipart.addBodyPart(messageBodyPart);

		MimeBodyPart attachmentBodyPart = new MimeBodyPart();
		try {
			attachmentBodyPart.attachFile(new File("C:\\Work\\Projects\\DynTechShop\\uplatnica.pdf"), "application/pdf", null);
			multipart.addBodyPart(attachmentBodyPart);
		} catch (IOException e) {
			e.printStackTrace();
		}

		MimeBodyPart contentMail = new MimeBodyPart();
		Template template = velocityEngine.getTemplate("./templates/" + templatePath);

		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("firstName", customer.getFirstName());
		velocityContext.put("lastName", customer.getLastName());

		StringWriter stringWriter = new StringWriter();
		template.merge(velocityContext, stringWriter);
		contentMail.setText(stringWriter.toString());

		multipart.addBodyPart(contentMail);

		message.setContent(multipart);
		mailSender.send(message);
	}
}
