package io.github.nishadchayanakhawa.testestimatehub.model.dto;

public class ChangeTypeConfigurationDTO {
	private long id;
	private String name;
	private double testCaseCountModifier;
	private double testStrategyPercentage;
	private double testPlanningPercentage;
	private double managementUpliftPercentage;
	public ChangeTypeConfigurationDTO() {
		super();
	}
	public ChangeTypeConfigurationDTO(String name, double testCaseCountModifier, double testStrategyPercentage,
			double testPlanningPercentage, double managementUpliftPercentage) {
		this();
		this.name = name;
		this.testCaseCountModifier = testCaseCountModifier;
		this.testStrategyPercentage = testStrategyPercentage;
		this.testPlanningPercentage = testPlanningPercentage;
		this.managementUpliftPercentage = managementUpliftPercentage;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTestCaseCountModifier() {
		return testCaseCountModifier;
	}
	public void setTestCaseCountModifier(double testCaseCountModifier) {
		this.testCaseCountModifier = testCaseCountModifier;
	}
	public double getTestStrategyPercentage() {
		return testStrategyPercentage;
	}
	public void setTestStrategyPercentage(double testStrategyPercentage) {
		this.testStrategyPercentage = testStrategyPercentage;
	}
	public double getTestPlanningPercentage() {
		return testPlanningPercentage;
	}
	public void setTestPlanningPercentage(double testPlanningPercentage) {
		this.testPlanningPercentage = testPlanningPercentage;
	}
	public double getManagementUpliftPercentage() {
		return managementUpliftPercentage;
	}
	public void setManagementUpliftPercentage(double managementUpliftPercentage) {
		this.managementUpliftPercentage = managementUpliftPercentage;
	}
}
