package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import io.github.nishadchayanakhawa.testestimatehub.model.Complexity;

public class ApplicationConfigurationDTO {
	private String applicationConfigurationIdApplicationName;
	private String applicationConfigurationIdModuleName;
	private String applicationConfigurationIdSubModuleName;
	private double baseTestCaseCountFactor;
	private Complexity complexity;
	public ApplicationConfigurationDTO() {
		super();
	}
	public ApplicationConfigurationDTO(String applicationName, String moduleName, String subModuleName,
			double baseTestCaseCountFactor, Complexity complexity) {
		this();
		this.applicationConfigurationIdApplicationName = applicationName;
		this.applicationConfigurationIdModuleName = moduleName;
		this.applicationConfigurationIdSubModuleName = subModuleName;
		this.baseTestCaseCountFactor = baseTestCaseCountFactor;
		this.complexity = complexity;
	}
	public String getApplicationConfigurationIdApplicationName() {
		return applicationConfigurationIdApplicationName;
	}
	public void setApplicationConfigurationIdApplicationName(String applicationConfigurationIdApplicationName) {
		this.applicationConfigurationIdApplicationName = applicationConfigurationIdApplicationName;
	}
	public String getApplicationConfigurationIdModuleName() {
		return applicationConfigurationIdModuleName;
	}
	public void setApplicationConfigurationIdModuleName(String applicationConfigurationIdModuleName) {
		this.applicationConfigurationIdModuleName = applicationConfigurationIdModuleName;
	}
	public String getApplicationConfigurationIdSubModuleName() {
		return applicationConfigurationIdSubModuleName;
	}
	public void setApplicationConfigurationIdSubModuleName(String applicationConfigurationIdSubModuleName) {
		this.applicationConfigurationIdSubModuleName = applicationConfigurationIdSubModuleName;
	}
	public double getBaseTestCaseCountFactor() {
		return baseTestCaseCountFactor;
	}
	public void setBaseTestCaseCountFactor(double baseTestCaseCountFactor) {
		this.baseTestCaseCountFactor = baseTestCaseCountFactor;
	}
	public Complexity getComplexity() {
		return complexity;
	}
	public void setComplexity(Complexity complexity) {
		this.complexity = complexity;
	}
	
}
