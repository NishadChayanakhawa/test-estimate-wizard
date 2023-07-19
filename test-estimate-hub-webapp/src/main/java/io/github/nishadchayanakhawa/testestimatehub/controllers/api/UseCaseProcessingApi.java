package io.github.nishadchayanakhawa.testestimatehub.controllers.api;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

import io.github.nishadchayanakhawa.testestimatehub.model.UseCase;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.UseCaseDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeService;

@RestController
@RequestMapping("/api/useCase")
public class UseCaseProcessingApi {
	private static final Logger logger=LoggerFactory.getLogger(UseCaseProcessingApi.class);
	
	@Autowired
	private ChangeService changeService;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	ModelMapper modelMapper;
	
	@PostMapping
	public ResponseEntity<String> addOrUpdate(@RequestBody List<UseCaseDTO> useCasesDTO) throws JsonProcessingException {
		List<UseCaseDTO> enrichedUseCasesDTO=useCasesDTO.stream()
				.map(u -> modelMapper.map(modelMapper.map(u, UseCase.class), UseCaseDTO.class))
				.toList();
		
		logger.info("CHANGE: {}",useCasesDTO.get(0).getChangeId());
		ChangeDTO change=changeService.get(enrichedUseCasesDTO.get(0).getChangeId());
		for(UseCaseDTO useCaseDTO : enrichedUseCasesDTO) {
			for(int iRequirement=0;iRequirement<change.getRequirements().size();iRequirement++) {
				if(change.getRequirements().get(iRequirement).getId()==useCaseDTO.getRequirementId()) {
					change.getRequirements().get(iRequirement).addUseCase(useCaseDTO);
				}
			}
		}
		logger.info("******Use case added change: {}",objectMapper.writeValueAsString(change));
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
