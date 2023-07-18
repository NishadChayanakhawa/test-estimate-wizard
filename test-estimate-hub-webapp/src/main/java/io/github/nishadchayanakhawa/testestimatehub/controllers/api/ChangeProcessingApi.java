package io.github.nishadchayanakhawa.testestimatehub.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeService;

@RestController
@RequestMapping("/api/change")
public class ChangeProcessingApi {
	@Autowired
	private ChangeService changeService;
	
	@PutMapping
	public ResponseEntity<ChangeDTO> addOrUpdate(@RequestBody ChangeDTO changeDTO) {
		HttpStatus httpStatus=changeService.exists(changeDTO)?
				HttpStatus.OK:HttpStatus.CREATED;
		return new ResponseEntity<>(changeService.addOrUpdate(changeDTO),httpStatus); 
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ChangeDTO>> getAll() {
		return new ResponseEntity<>(changeService.getAll(),HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ChangeDTO> get(@PathVariable Long id) {
		return new ResponseEntity<>(changeService.get(id),HttpStatus.OK); 
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody ChangeDTO changeDTO) {
		changeService.delete(changeDTO);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
