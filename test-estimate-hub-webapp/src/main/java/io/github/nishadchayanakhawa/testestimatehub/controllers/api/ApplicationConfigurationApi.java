package io.github.nishadchayanakhawa.testestimatehub.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ApplicationConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.ApplicationConfigurationService;

@RestController
@RequestMapping("/api/config/application")
public class ApplicationConfigurationApi {
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;
	
	@GetMapping
	public ResponseEntity<Iterable<ApplicationConfigurationDTO>> getApplicationConfigurations() {
		return new ResponseEntity<>(applicationConfigurationService.getAll(),HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<ApplicationConfigurationDTO> getApplicationConfiguration(@RequestBody ApplicationConfigurationDTO applicationConfigurationDTO) {
		return new ResponseEntity<>(applicationConfigurationService.get(applicationConfigurationDTO),HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteApplicationConfiguration(@RequestBody ApplicationConfigurationDTO applicationConfigurationDTO) {
		applicationConfigurationService.delete(applicationConfigurationDTO);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<ApplicationConfigurationDTO> addOrUpdate(@RequestBody ApplicationConfigurationDTO applicationConfigurationDTO) {
		HttpStatus httpStatus=applicationConfigurationService.exists(applicationConfigurationDTO)?
				HttpStatus.OK:HttpStatus.CREATED;
		return new ResponseEntity<>(applicationConfigurationService.add(applicationConfigurationDTO),httpStatus); 
	}
}
