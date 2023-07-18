package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ReleaseDTO {
	private long id;
	private String identifier;
	private String name;
}
