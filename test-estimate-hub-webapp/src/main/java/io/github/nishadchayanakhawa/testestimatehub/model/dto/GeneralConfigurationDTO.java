package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GeneralConfigurationDTO {
	private double testCasePreparationProductivityForVeryLowComplexity;
	private double testCasePreparationProductivityForLowComplexity;
	private double testCasePreparationProductivityForMediumComplexity;
	private double testCasePreparationProductivityForHighComplexity;
	private double testCasePreparationProductivityForVeryHighHighComplexity;

	private double testCaseExecutionProductivityForVeryLowComplexity;
	private double testCaseExecutionProductivityForLowComplexity;
	private double testCaseExecutionProductivityForMediumComplexity;
	private double testCaseExecutionProductivityForHighComplexity;
	private double testCaseExecutionProductivityForVeryHighHighComplexity;
	
	private double automationNewDesignProductivityForVeryLowComplexity;
	private double automationNewDesignProductivityForLowComplexity;
	private double automationNewDesignProductivityForMediumComplexity;
	private double automationNewDesignProductivityForHighComplexity;
	private double automationNewDesignProductivityForVeryHighHighComplexity;
	
	private double automationMaintenanceProductivityForVeryLowComplexity;
	private double automationMaintenanceProductivityForLowComplexity;
	private double automationMaintenanceProductivityForMediumComplexity;
	private double automationMaintenanceProductivityForHighComplexity;
	private double automationMaintenanceProductivityForVeryHighHighComplexity;
	
	private double requirementModifierForVeryLowComplexity;
	private double requirementModifierForLowComplexity;
	private double requirementModifierForMediumComplexity;
	private double requirementModifierForHighComplexity;
	private double requirementModifierForVeryHighHighComplexity;
}
