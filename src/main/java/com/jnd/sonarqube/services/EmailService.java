package com.jnd.sonarqube.services;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService {

	@Autowired
	private JavaMailSender sender;

	public void sendEmail(File file, String email_to) throws Exception {
		MimeMessage message = sender.createMimeMessage();

		// Enable the multipart flag!
		MimeMessageHelper helper = new MimeMessageHelper(message, true);

		helper.setTo(email_to);
		helper.setText("<html><body>Here is your SonarQube report <body></html>", true);
		helper.setSubject("SonarQube Report -" + file.getName());

		ClassPathResource file_to_send = new ClassPathResource(file.getPath());
		helper.addAttachment(file.getName(), file_to_send);

		sender.send(message);
	}

}
