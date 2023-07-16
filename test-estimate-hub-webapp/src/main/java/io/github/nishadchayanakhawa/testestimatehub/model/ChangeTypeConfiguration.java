package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ChangeTypeConfiguration {
	@Id
	@GeneratedValue
	private long id;
	@Column(unique=true)
	private String name;
	private double testCaseCountModifier;
	private double testStrategyPercentage;
	private double testPlanningPercentage;
	private double managementUpliftPercentage;
	public ChangeTypeConfiguration() {
		super();
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
