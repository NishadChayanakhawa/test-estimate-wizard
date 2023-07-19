package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UseCaseDTO {
	private long id;
	private String description;
	private String testDataComplexityCode;
	private String testDataComplexityDisplayValue;
	private String testConfigurationComplexityCode;
	private String testConfigurationComplexityDisplayValue;
	private String testValidationComplexityCode;
	private String testValidationComplexityDisplayValue;
}
