package io.github.nishadchayanakhawa.testestimatehub.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeTypeConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeTypeConfigurationServices;

@RestController
@RequestMapping("/api/config/changeType")
public class ChangeTypeConfigurationApi {
	@Autowired
	private ChangeTypeConfigurationServices changeTypeConfigurationServices;

	@PutMapping
	public ResponseEntity<ChangeTypeConfigurationDTO> addOrUpdate(@RequestBody ChangeTypeConfigurationDTO changeTypeConfigurationDTO) {
		HttpStatus httpStatus=changeTypeConfigurationServices.exists(changeTypeConfigurationDTO)?
				HttpStatus.OK:HttpStatus.CREATED;
		return new ResponseEntity<>(changeTypeConfigurationServices.addOrUpdate(changeTypeConfigurationDTO),httpStatus); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ChangeTypeConfigurationDTO> get(@PathVariable long id) {
		return new ResponseEntity<>(changeTypeConfigurationServices.get(id),HttpStatus.OK); 
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ChangeTypeConfigurationDTO>> getAll() {
		return new ResponseEntity<>(changeTypeConfigurationServices.getAll(),HttpStatus.OK); 
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody ChangeTypeConfigurationDTO changeTypeConfigurationDTO) {
		changeTypeConfigurationServices.delete(changeTypeConfigurationDTO);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
