package com.jnd.sonarqube.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import com.jnd.sonarqube.beans.MeasuresBean;
import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.utils.SonarQubeConstants;

public class MeasuresService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ServerBean serverBean;
	
	public MeasuresBean fetchProjectMeasures(String project_key) {
		String project_measures_url = serverBean.getSonarQubeUrl() + SonarQubeConstants.PROJECT_MEASURES_API;
		
		Map<String, String> vars = new HashMap<>();
		vars.put("additionalFields", "periods");
		vars.put("component", "project_key");
		vars.put("metricKeys", SonarQubeConstants.SONAR_METRICS);
		MeasuresBean measuresBean = restTemplate.getForObject(project_measures_url, MeasuresBean.class);
		return measuresBean;
	}

}
