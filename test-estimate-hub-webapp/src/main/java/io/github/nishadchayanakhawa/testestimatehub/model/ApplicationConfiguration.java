package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity
public class ApplicationConfiguration {
	@EmbeddedId
	private ApplicationConfigurationID applicationConfigurationId;
	private double baseTestCaseCountFactor;
	@Enumerated(EnumType.STRING)
	private Complexity complexity;
	public ApplicationConfiguration() {
		super();
	}
	public ApplicationConfigurationID getApplicationConfigurationId() {
		return applicationConfigurationId;
	}
	public void setApplicationConfigurationId(ApplicationConfigurationID applicationConfigurationId) {
		this.applicationConfigurationId = applicationConfigurationId;
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