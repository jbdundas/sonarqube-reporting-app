package com.jnd.sonarqube;

import java.util.Properties;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.services.EmailService;
import com.jnd.sonarqube.services.MeasuresService;
import com.jnd.sonarqube.services.PoiService;

@Configuration
@ComponentScan(basePackages = "com.jnd.sonarqube")
public class SonarQubeAppConfig {

	@Bean(name="restTemplate")
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean(name="measuresService")
	public MeasuresService measuresService() {
		return new MeasuresService();
	}
	
	@Bean(name="poiService")
	public PoiService poiService() {
		return new PoiService();
	}
	
	@Bean(name="serverBean")
	public ServerBean serverBean() {
		return new ServerBean();
	}
	
	@Bean
	public JavaMailSender mailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost("smtp.gmail.com");
	    mailSender.setPort(587);
	     
	    mailSender.setUsername("my.gmail@gmail.com");
	    mailSender.setPassword("password");
	     
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true");
	     
	    return mailSender;
	}
	
	@Bean(name="emailService")
	public EmailService emailService() {
		return new EmailService();
	}
}
