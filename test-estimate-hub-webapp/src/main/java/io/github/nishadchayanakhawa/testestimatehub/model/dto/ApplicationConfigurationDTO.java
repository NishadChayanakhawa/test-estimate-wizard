package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ApplicationConfigurationDTO {
	private long id;
	private String applicationName;
	private String moduleName;
	private String subModuleName;
	private double baseTestCaseCountFactor;
	private String complexityCode;
	private String complexityDisplayValue;
}
