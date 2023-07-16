package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class TestTypeDTO {
	private long id;
	private String name;
	private double relativeTestCasePercentage;
	private double reExecutionPercentage;
	private double additionalCycleExecutionPercentage;
}
