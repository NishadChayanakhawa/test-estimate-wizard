package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "applicationName", "moduleName", "subModuleName" }) })
public class ApplicationConfiguration {
	@Id
	@GeneratedValue
	private long id;
	private String applicationName;
	private String moduleName;
	private String subModuleName;
	private double baseTestCaseCountFactor;
	@Enumerated(EnumType.STRING)
	private Complexity complexity;
}