package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class ValueByComplexity {
	@Id
	@GeneratedValue
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private long id;
	private double forVeryLowComplexity;
	private double forLowComplexity;
	private double forMediumComplexity;
	private double forHighComplexity;
	private double forVeryHighComplexity;
}
