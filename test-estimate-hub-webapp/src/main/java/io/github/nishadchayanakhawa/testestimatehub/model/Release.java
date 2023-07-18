package io.github.nishadchayanakhawa.testestimatehub.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
public class Release {
	@Id
	private String id;
	private String name;
}
