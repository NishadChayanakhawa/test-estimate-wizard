package io.github.nishadchayanakhawa.testestimatehub.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.GeneralConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.GeneralConfigurationService;

@RestController
@RequestMapping("/api/config/general")
public class GeneralConfigurationApi {
	@Autowired
	private GeneralConfigurationService generalConfigurationService;
	
	@PutMapping
	public ResponseEntity<GeneralConfigurationDTO> update(@RequestBody GeneralConfigurationDTO generalConfigurationDTO) {
		return 
			new ResponseEntity<>
				(generalConfigurationService.save(generalConfigurationDTO),HttpStatus.OK); 
	}
	
	@GetMapping
	public ResponseEntity<GeneralConfigurationDTO> get() {
		return new ResponseEntity<>(generalConfigurationService.get(),HttpStatus.OK); 
	}
}
