package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class RequirementDTO {
	private long id;
	private String identifier;
	private String description;
	private String complexityCode;
	private String complexityDisplayValue;
	private List<UseCaseDTO> useCases;
	
	public void addUseCase(UseCaseDTO useCase) {
		if(useCases==null) {
			useCases=new ArrayList<>();
		}
		useCases.add(useCase);
	}
}
