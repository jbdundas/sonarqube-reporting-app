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

	public static final Logger LOG = LogManager.getLogger(MeasuresService.class);
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ServerBean serverBean;
	
	public MeasuresBean fetchProjectMeasures(String project_key) {
		
		LOG.info("serverBean="+serverBean.toString());		
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
