package com.jnd.sonarqube.services;

import java.net.URI;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jnd.sonarqube.beans.MeasuresBean;
import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.utils.SonarQubeConstants;

public class MeasuresService {

	public static final Logger Logger = LogManager.getLogger(MeasuresService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ServerBean serverBean;
	
	public MeasuresBean fetchProjectMeasures(String projectKey) {
		
		if (Logger.isDebugEnabled()) {
			Logger.debug("serverBean -> {}",serverBean);
		}
				
		URI projectMeasuresUrl = UriComponentsBuilder.fromUriString(serverBean.getSonarQubeUrl())
									.path(SonarQubeConstants.PROJECT_MEASURES_API)
									.queryParam("metricKeys", SonarQubeConstants.SONAR_METRICS)
									.queryParam("component", projectKey)
									.queryParam("additionalFields", "periods")
									.build()
									.encode()
									.toUri();
		
		//FIXME: Replace this call with the sonar-ws-client wrapper client to make it easier for reading and making API calls with SonarQube.
		return restTemplate.getForObject(projectMeasuresUrl, MeasuresBean.class);
	}

}
