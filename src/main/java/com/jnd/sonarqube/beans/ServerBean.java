package com.jnd.sonarqube.beans;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerBean {
	
	public static final Logger LOG = LogManager.getLogger(ServerBean.class);
	
    @Value("${sonar.server.url}")
	private String sonarQubeUrl;
    
    @Value("${sonar.server.username}")
	private String sonarQubeUserName;
    
    @Value("${sonar.server.password}")
	private String sonarQubeUserPwd;

	public ServerBean() {
	}
	
	public String getSonarQubeUrl() {
		return sonarQubeUrl;
	}

	public void setSonarQubeUrl(String sonarQubeUrl) {
		this.sonarQubeUrl = sonarQubeUrl;
	}

	public String getSonarQubeUserName() {
		return sonarQubeUserName;
	}

	public void setSonarQubeUserName(String sonarQubeUserName) {
		this.sonarQubeUserName = sonarQubeUserName;
	}

	public String getSonarQubeUserPwd() {
		return sonarQubeUserPwd;
	}

	public void setSonarQubeUserPwd(String sonarQubeUserPwd) {
		this.sonarQubeUserPwd = sonarQubeUserPwd;
	}

    @Autowired
	public ServerBean(@Value("${sonar.server.url}") String sonarQubeUrl,@Value("${sonar.server.username}") String sonarQubeUserName, @Value("${sonar.server.username}") String sonarQubeUserPwd) {
		this.sonarQubeUrl = sonarQubeUrl;
		this.sonarQubeUserName = sonarQubeUserName;
		this.sonarQubeUserPwd = sonarQubeUserPwd;
		LOG.info("ServerBean = "+ this.toString());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sonarQubeUrl == null) ? 0 : sonarQubeUrl.hashCode());
		result = prime * result + ((sonarQubeUserName == null) ? 0 : sonarQubeUserName.hashCode());
		result = prime * result + ((sonarQubeUserPwd == null) ? 0 : sonarQubeUserPwd.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServerBean other = (ServerBean) obj;
		if (sonarQubeUrl == null) {
			if (other.sonarQubeUrl != null)
				return false;
		} else if (!sonarQubeUrl.equals(other.sonarQubeUrl))
			return false;
		if (sonarQubeUserName == null) {
			if (other.sonarQubeUserName != null)
				return false;
		} else if (!sonarQubeUserName.equals(other.sonarQubeUserName))
			return false;
		if (sonarQubeUserPwd == null) {
			if (other.sonarQubeUserPwd != null)
				return false;
		} else if (!sonarQubeUserPwd.equals(other.sonarQubeUserPwd))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ServerBean [sonarQubeUrl=" + sonarQubeUrl + ", sonarQubeUserName=" + sonarQubeUserName + "]";
	}
    
	
}
