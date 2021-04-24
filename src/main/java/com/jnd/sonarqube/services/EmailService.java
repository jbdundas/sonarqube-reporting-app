package com.jnd.sonarqube.services;

import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailService {

	@Autowired
	private JavaMailSender sender;

	public void sendEmail(String filePath, String emailTo, String projectKey) {
		MimeMessage message = sender.createMimeMessage();
		File file = new File(filePath);
		// Enable the multipart flag!
		MimeMessageHelper helper;
		try {
			helper = new MimeMessageHelper(message, true);
			helper.setTo(emailTo);
			helper.setText("<html><body><br>Hi there,</br><br>Here is your SonarQube report attached with this email. </br><br>Regards,</br>SonarQube Team<body></html>", true);
			helper.setSubject(String.format("SonarQube Report for %s", projectKey) );
			helper.addAttachment(file.getName(), new File(filePath));
		} catch (MessagingException e) {
			e.printStackTrace();
		}

		sender.send(message);
	}

}
