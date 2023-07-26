package io.github.nishadchayanakhawa.testestimatehub.controllers.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.UseCaseDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeService;

@RestController
@RequestMapping("/api/useCase")
public class UseCaseProcessingApi {	
	@Autowired
	private ChangeService changeService;
	
	
	@Autowired
	ModelMapper modelMapper;
	
	@PutMapping
	public ResponseEntity<ChangeDTO> addOrUpdate(@RequestBody List<UseCaseDTO> useCasesDTO) throws JsonProcessingException {
		return new ResponseEntity<>(changeService.saveUseCases(useCasesDTO),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ChangeDTO> submitForReview(@RequestBody List<UseCaseDTO> useCasesDTO) throws JsonProcessingException {
		return new ResponseEntity<>(changeService.submitEstimationForReview(useCasesDTO),HttpStatus.OK);
	}
}
