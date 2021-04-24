package com.jnd.sonarqube;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.services.EmailService;
import com.jnd.sonarqube.services.MeasuresService;
import com.jnd.sonarqube.services.PoiService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SonarQubeAppTest {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MeasuresService measuresService;
	
	@Autowired
	private PoiService poiService;
	
	@Autowired
	private ServerBean serverBean;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Test
	public void checkIfSonarQubeIsUp() {
		String sq_health_url = "http://localhost:9000/api/system/ping";
		String response = restTemplate.getForObject(sq_health_url , String.class);
		assertTrue(response.contains("pong"));
	}
}
