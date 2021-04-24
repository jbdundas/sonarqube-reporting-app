package com.jnd.sonarqube;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.client.RestTemplate;

import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.services.EmailService;
import com.jnd.sonarqube.services.MeasuresService;
import com.jnd.sonarqube.services.PoiService;
import com.jnd.sonarqube.utils.SonarQubeConstants;

@Configuration
@ComponentScan(basePackages = "com.jnd.sonarqube")
public class SonarQubeAppConfig {

    @Autowired
    private Environment env;
    
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
    
	@Value("${smtp.mail.password}")
	private String smtp_mail_password;
	
	@Value("${smtp.mail.username}")
	private String smtp_mail_username;
	
	@Bean
	public JavaMailSender mailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    
        mailSender.setHost(env.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(env.getProperty(SonarQubeConstants.SMTP_MAIL_TRANSPORT_PORT)));
		mailSender.setUsername(smtp_mail_username);
		mailSender.setPassword(smtp_mail_password);
 
        Properties props = mailSender.getJavaMailProperties();
		props.put(SonarQubeConstants.SMTP_MAIL_TRANSPORT_PROTOCOL,env.getProperty(SonarQubeConstants.SMTP_MAIL_TRANSPORT_PROTOCOL));
		props.put(SonarQubeConstants.MAIL_SMTP_AUTH, Boolean.valueOf(env.getProperty(SonarQubeConstants.MAIL_SMTP_AUTH)));
		props.put(SonarQubeConstants.MAIL_SMTP_STARTTLS_ENABLE_PROP_KEY, Boolean.valueOf(env.getProperty(SonarQubeConstants.MAIL_SMTP_STARTTLS_ENABLE_PROP_TEXT)));
		props.put(SonarQubeConstants.MAIL_DEBUG, Boolean.valueOf(env.getProperty(SonarQubeConstants.MAIL_DEBUG)));
		props.put(SonarQubeConstants.MAIL_SMTP_CONN_TIMEOUT,env.getProperty(SonarQubeConstants.MAIL_SMTP_CONN_TIMEOUT));
		props.put(SonarQubeConstants.MAIL_SMTP_TIMEOUT, env.getProperty(SonarQubeConstants.MAIL_SMTP_TIMEOUT));
		props.put(SonarQubeConstants.MAIL_SMTP_WRITE_TIMEOUT,env.getProperty(SonarQubeConstants.MAIL_SMTP_WRITE_TIMEOUT));
				
        mailSender.setJavaMailProperties(props);
	    return mailSender;
	}
	
	@Bean(name="emailService")
	public EmailService emailService() {
		return new EmailService();
	}
}
