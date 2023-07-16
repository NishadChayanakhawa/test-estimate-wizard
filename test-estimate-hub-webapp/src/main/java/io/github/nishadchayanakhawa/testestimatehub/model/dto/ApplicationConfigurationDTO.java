package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "applicationName", "moduleName", "subModuleName", "baseTestCaseCountFactor", "complexity",
		"complexityDisplayValue" })
public class ApplicationConfigurationDTO {
	@JsonProperty("applicationName")
	private String applicationConfigurationIdApplicationName;
	@JsonProperty("moduleName")
	private String applicationConfigurationIdModuleName;
	@JsonProperty("subModuleName")
	private String applicationConfigurationIdSubModuleName;
	private double baseTestCaseCountFactor;
	private String complexityCode;
	private String complexityDisplayValue;
	public ApplicationConfigurationDTO() {
		super();
	}

	public ApplicationConfigurationDTO(String applicationName, String moduleName, String subModuleName,
			double baseTestCaseCountFactor, String complexityCode) {
		this();
		this.applicationConfigurationIdApplicationName = applicationName;
		this.applicationConfigurationIdModuleName = moduleName;
		this.applicationConfigurationIdSubModuleName = subModuleName;
		this.baseTestCaseCountFactor = baseTestCaseCountFactor;
		this.complexityCode = complexityCode;
	}

	public String getComplexityDisplayValue() {
		return complexityDisplayValue;
	}

	public void setComplexityDisplayValue(String complexityDisplayValue) {
		this.complexityDisplayValue = complexityDisplayValue;
	}

	public String getComplexityCode() {
		return complexityCode;
	}

	public void setComplexityCode(String complexityCode) {
		this.complexityCode = complexityCode;
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
}
