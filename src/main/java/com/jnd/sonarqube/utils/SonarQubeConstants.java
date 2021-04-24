package com.jnd.sonarqube.utils;

public class SonarQubeConstants {
	
	public static final String PROJECT_MEASURES_API = "/api/measures/component";

	/*
	   "msg": "The following metric keys are not found: security_hotspots_reviewed, conditions_by_line, branch_coverage_hits_data"
	 */
	public static final String SONAR_METRICS = "complexity,cognitive_complexity,duplicated_blocks,duplicated_files,duplicated_lines,duplicated_lines_density,"
			+ "new_violations,new_blocker_violations,new_critical_violations,new_major_violations,new_minor_violations,new_info_violations,"
			+ "violations,blocker_violations,critical_violations,major_violations,minor_violations,info_violations,"
			+ "false_positive_issues,open_issues,confirmed_issues,reopened_issues,code_smells,new_code_smells,sqale_rating,"
			+ "sqale_index,new_technical_debt,sqale_debt_ratio,new_sqale_debt_ratio,alert_status,quality_gate_details,"
			+ "bugs,new_bugs,reliability_rating,reliability_remediation_effort,new_reliability_remediation_effort,"
			+ "vulnerabilities,new_vulnerabilities,security_rating,security_remediation_effort,new_security_remediation_effort,"
			+ "security_hotspots,new_security_hotspots,security_review_rating,"
			+ "classes,comment_lines,comment_lines_density,directories,files,lines,ncloc,"
			+ "ncloc_language_distribution,functions,projects,statements,branch_coverage,new_branch_coverage,"
			+ "coverage,new_coverage,"
			+ "line_coverage,new_line_coverage,lines_to_cover,new_lines_to_cover,skipped_tests,"
			+ "uncovered_conditions,new_uncovered_conditions,uncovered_lines,new_uncovered_lines,tests,"
			+ "test_execution_time,test_execution_time,test_failures,test_success_density";

	public static final String MAIL_DEBUG = "spring.mail.properties.mail.debug";
	public static final String MAIL_SMTP_STARTTLS_ENABLE_PROP_KEY = "mail.smtp.starttls.enable";
	public static final String MAIL_SMTP_STARTTLS_ENABLE_PROP_TEXT = "spring.mail.properties.mail.smtp.starttls.enable";
	public static final String MAIL_SMTP_AUTH = "spring.mail.properties.mail.smtp.auth";
	public static final String SMTP_MAIL_TRANSPORT_PROTOCOL = "spring.mail.properties.mail.transport.protocol";
	public static final String SMTP_MAIL_TRANSPORT_PORT = "spring.mail.port";
	public static final String SMTP_MAIL_TRANSPORT_HOST = "smtp.gmail.com";
	public static final String SMTP_GMAIL_USERNAME = "spring.mail.username";
	public static final String SMTP_GMAIL_PASSWORD = "spring.mail.password";
	public static final String MAIL_SMTP_CONN_TIMEOUT = "spring.mail.properties.mail.smtp.connectiontimeout";
	public static final String MAIL_SMTP_TIMEOUT = "spring.mail.properties.mail.smtp.timeout";
	public static final String MAIL_SMTP_WRITE_TIMEOUT = "spring.mail.properties.mail.smtp.writetimeout";
			
}
