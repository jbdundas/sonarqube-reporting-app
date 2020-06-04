package com.jnd.sonarqube.services;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.jnd.sonarqube.beans.MeasuresBean;
import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.utils.SonarQubeConstants;

public class MeasuresService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ServerBean serverBean;
	
	public MeasuresBean fetchProjectMeasures(String project_key) {
		serverBean.init();
		
		//String project_measures_url = serverBean.getSonarQubeUrl() + SonarQubeConstants.PROJECT_MEASURES_API;
		
		URI project_measures_url = UriComponentsBuilder.fromUriString(serverBean.getSonarQubeUrl())
									.path(SonarQubeConstants.PROJECT_MEASURES_API)
									.queryParam("metricKeys", SonarQubeConstants.SONAR_METRICS)
									.queryParam("component", project_key)
									.queryParam("additionalFields", "periods")
									.build()
									.encode()
									.toUri();
		MeasuresBean measuresBean = restTemplate.getForObject(project_measures_url, MeasuresBean.class);
		return measuresBean;
	}

}
