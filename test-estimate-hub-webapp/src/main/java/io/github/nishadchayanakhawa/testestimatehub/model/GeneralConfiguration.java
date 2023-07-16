package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class GeneralConfiguration {
	@Id
	@GeneratedValue
	private long id;
	@ManyToOne(cascade=CascadeType.ALL)
	private ValueByComplexity testCasePreparationProductivity;
	@ManyToOne(cascade=CascadeType.ALL)
	private ValueByComplexity testCaseExecutionProductivity;
	@ManyToOne(cascade=CascadeType.ALL)
	private ValueByComplexity automationNewDesignProductivity;
	@ManyToOne(cascade=CascadeType.ALL)
	private ValueByComplexity automationMaintenanceProductivity;
	@ManyToOne(cascade=CascadeType.ALL)
	private ValueByComplexity requirementModifierPercentage;
	
	private double complexityWeightagePercentageForTestDataPreparation;
	private double complexityWeightagePercentageForTestConfiguration;
	private double complexityWeightagePercentageForTestValidation;
}
