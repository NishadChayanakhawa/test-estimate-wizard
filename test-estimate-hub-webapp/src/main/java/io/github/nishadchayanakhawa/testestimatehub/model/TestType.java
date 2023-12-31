package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class TestType {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String name;
	private double relativeTestCasePercentage;
	private double reExecutionPercentage;
	private double additionalCycleExecutionPercentage;
}
