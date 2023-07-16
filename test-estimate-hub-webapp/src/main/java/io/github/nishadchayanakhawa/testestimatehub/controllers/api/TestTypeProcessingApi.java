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
import io.github.nishadchayanakhawa.testestimatehub.model.dto.TestTypeDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.TestTypeService;

@RestController
@RequestMapping("/api/config/testType")
public class TestTypeProcessingApi {
	@Autowired
	private TestTypeService testTypeService;
	
	@PutMapping
	public ResponseEntity<TestTypeDTO> addOrUpdate(@RequestBody TestTypeDTO testTypeDTO) {
		HttpStatus httpStatus=testTypeService.exists(testTypeDTO)?
				HttpStatus.OK:HttpStatus.CREATED;
		return new ResponseEntity<>(testTypeService.addOrUpdate(testTypeDTO),httpStatus); 
	}
	
	@GetMapping
	public ResponseEntity<Iterable<TestTypeDTO>> getAll() {
		return new ResponseEntity<>(testTypeService.getAll(),HttpStatus.OK); 
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<TestTypeDTO> get(@PathVariable long id) {
		return new ResponseEntity<>(testTypeService.get(id),HttpStatus.OK); 
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete(@RequestBody TestTypeDTO testTypeDTO) {
		testTypeService.delete(testTypeDTO);
		return new ResponseEntity<>(HttpStatus.OK); 
	}
}
