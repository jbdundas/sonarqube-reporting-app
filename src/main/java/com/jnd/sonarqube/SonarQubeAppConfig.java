package com.jnd.sonarqube;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.jnd.sonarqube.beans.ServerBean;
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
}
