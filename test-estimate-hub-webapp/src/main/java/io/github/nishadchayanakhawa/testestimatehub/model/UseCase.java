package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class UseCase {
	@Id
	@GeneratedValue
	private long id;
	private String description;
	@Enumerated(EnumType.STRING)
	private Complexity testDataComplexity;
	@Enumerated(EnumType.STRING)
	private Complexity testConfigurationComplexity;
	@Enumerated(EnumType.STRING)
	private Complexity testValidationComplexity;
}
