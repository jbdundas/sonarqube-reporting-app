package com.jnd.sonarqube;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.jnd.sonarqube.beans.MeasuresBean;
import com.jnd.sonarqube.beans.ServerBean;
import com.jnd.sonarqube.services.EmailService;
import com.jnd.sonarqube.services.MeasuresService;
import com.jnd.sonarqube.services.PoiService;

@Controller
public class SonarQubeController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MeasuresService measuresService;
	
	@Autowired
	private PoiService poiService;
	
	@Autowired
	private ServerBean serverBean;
	
	@Autowired
	private EmailService emailService;
	
	@RequestMapping(value = "/export_project_measures", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  export_project_measures(@RequestParam(name="project_key", required=true, defaultValue="all") String project_key,@RequestParam(name="email_to", required=false, defaultValue="all") String email_to, Model model) {
		LOG.info("Export project measures for -> "+ project_key + " # serverBean -> "+ serverBean.toString());
		MeasuresBean measuresBean = measuresService.fetchProjectMeasures(project_key);
		//export to excel..
		File report_file = poiService.exportProjectMeasuresReport(measuresBean);
		
		model.addAttribute("project_key",project_key );
		model.addAttribute("report_file", report_file);
		
		try {
			emailService.sendEmail(report_file,email_to);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new FileSystemResource(report_file); 
	}
	
}
