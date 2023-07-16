package io.github.nishadchayanakhawa.testestimatehub.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@SuppressWarnings("serial")
@Embeddable
public class ApplicationConfigurationID implements Serializable {
	private String applicationName;
	private String moduleName;
	private String subModuleName;
	public ApplicationConfigurationID() {
		super();
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getSubModuleName() {
		return subModuleName;
	}
	public void setSubModuleName(String subModuleName) {
		this.subModuleName = subModuleName;
	}
}
