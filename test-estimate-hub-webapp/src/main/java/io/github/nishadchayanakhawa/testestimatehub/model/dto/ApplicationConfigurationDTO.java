package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.github.nishadchayanakhawa.testestimatehub.model.Complexity;

@JsonPropertyOrder({
	"applicationName",
	"moduleName",
	"subModuleName",
	"baseTestCaseCountFactor",
	"complexity",
	"complexityDisplayValue"
})
public class ApplicationConfigurationDTO {
	@JsonProperty("applicationName")
	private String applicationConfigurationIdApplicationName;
	@JsonProperty("moduleName")
	private String applicationConfigurationIdModuleName;
	@JsonProperty("subModuleName")
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
