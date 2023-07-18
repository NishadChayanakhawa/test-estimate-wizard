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

import io.github.nishadchayanakhawa.testestimatehub.model.dto.ReleaseDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.ReleaseService;

@RestController
@RequestMapping("/api/release")
public class ReleaseProcessingApi {
	@Autowired
	private ReleaseService releaseService;
	
	@PutMapping
	public ResponseEntity<ReleaseDTO> addOrUpdate(@RequestBody ReleaseDTO releaseDTO) {
		HttpStatus httpStatus=releaseService.exists(releaseDTO)?
				HttpStatus.OK:HttpStatus.CREATED;
		return new ResponseEntity<>(releaseService.addOrUpdate(releaseDTO),httpStatus); 
	}
	
	@GetMapping
	public ResponseEntity<Iterable<ReleaseDTO>> getAll() {
		return new ResponseEntity<>(releaseService.getAll(),HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ReleaseDTO> get(@PathVariable String id) {
		return new ResponseEntity<>(releaseService.get(id),HttpStatus.OK); 
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody ReleaseDTO releaseDTO) {
		releaseService.delete(releaseDTO);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
