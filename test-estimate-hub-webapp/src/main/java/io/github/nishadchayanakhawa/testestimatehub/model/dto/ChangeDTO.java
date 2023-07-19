package io.github.nishadchayanakhawa.testestimatehub.model.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ChangeDTO {
	private long id;
	private String identifier;
	private String name;
	private String statusCode;
	private String statusDisplayValue;
	private long releaseId;
	private String releaseIdentifier;
	private String releaseName;
	private List<RequirementDTO> requirements;
	private LocalDate startDate;
	private LocalDate endDate;
	private Long changeTypeId;
	private String changeTypeName;
}
