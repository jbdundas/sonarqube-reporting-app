package com.jnd.sonarqube;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.util.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jnd.sonarqube.beans.MeasuresBean;
import com.jnd.sonarqube.services.MeasuresService;
import com.jnd.sonarqube.services.PoiService;

@Controller
public class SonarQubeController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MeasuresService measuresService;
	
	@Autowired
	private PoiService poiService;
	
	@GetMapping("/greeting")
	public String greeting(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}

	@RequestMapping(value="/generate_sonarqube_report")
	public void getLogFile(@RequestParam(name="project_key", required=true, defaultValue="all") String project_key, HttpSession session,HttpServletResponse response) throws Exception {
	    try {
	    	LOG.info("Export project measures for -> "+ project_key);
			MeasuresBean measuresBean = measuresService.fetchProjectMeasures(project_key);
			//export to excel..
			File report_file = poiService.exportProjectMeasuresReport(measuresBean);
	        InputStream inputStream = new FileInputStream(report_file);
	        response.setContentType("application/force-download");
	        response.setHeader("Content-Disposition", "attachment; filename="+report_file.getName()); 
	        IOUtils.copy(inputStream, response.getOutputStream());
	        response.flushBuffer();
	        inputStream.close();
	    } catch (Exception e){
	    	LOG.debug("Request could not be completed at this moment. Please try again.");
	        e.printStackTrace();
	    }
	}
	
	@RequestMapping(value = "/export_project_measures", method = RequestMethod.GET)
	@ResponseBody
	public FileSystemResource  export_project_measures(@RequestParam(name="project_key", required=true, defaultValue="all") String project_key, Model model) {
		LOG.info("Export project measures for -> "+ project_key);
		MeasuresBean measuresBean = measuresService.fetchProjectMeasures(project_key);
		//export to excel..
		File report_file = poiService.exportProjectMeasuresReport(measuresBean);
		
		model.addAttribute("project_key",project_key );
		model.addAttribute("report_file", report_file);
		
		return new FileSystemResource(report_file); 
	}
	
}
