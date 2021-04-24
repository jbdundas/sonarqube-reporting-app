package com.jnd.sonarqube;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jnd.sonarqube.beans.MeasuresBean;
import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.services.EmailService;
import com.jnd.sonarqube.services.MeasuresService;
import com.jnd.sonarqube.services.PoiService;

@Controller
public class SonarQubeController {

	private final Logger Logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MeasuresService measuresService;
	
	@Autowired
	private PoiService poiService;
	
	@Autowired
	private ServerBean serverBean;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping(value = "/export_project_measures")
	@ResponseBody
	public FileSystemResource exportProjectMeasures(@RequestParam(name="project_key", required=true, defaultValue="all") String projectKey,@RequestParam(name="email_to", required=false, defaultValue="jiteshdundas@yahoo.com") String emailTo, Model model) {
		
		if (Logger.isDebugEnabled()) {
			Logger.debug("Export project measures for -> {} # serverBean -> {}",projectKey,serverBean);
		}
		
		MeasuresBean measuresBean = measuresService.fetchProjectMeasures(projectKey);
		//export to excel..
		File reportFile = poiService.exportProjectMeasuresReport(measuresBean);
		
		model.addAttribute("project_key",projectKey );
		model.addAttribute("report_file", reportFile);
		
		try {
			emailService.sendEmail(reportFile.getCanonicalPath(),emailTo,projectKey);
		} catch (Exception e) {
			Logger.error("Exception while sending email", e);
		}
		
		return new FileSystemResource(reportFile); 
	}
	
}
