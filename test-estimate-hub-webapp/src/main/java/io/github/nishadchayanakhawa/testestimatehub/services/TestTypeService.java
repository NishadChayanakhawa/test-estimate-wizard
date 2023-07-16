package io.github.nishadchayanakhawa.testestimatehub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nishadchayanakhawa.testestimatehub.jparepositories.TestTypeRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.TestType;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.TestTypeDTO;

@Service
public class TestTypeService {
	@Autowired
	private TestTypeRepository testTypeRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public boolean exists(TestTypeDTO testTypeDTO) {
		return testTypeRepository.existsById(testTypeDTO.getId());
	}
	
	public TestTypeDTO addOrUpdate(TestTypeDTO testTypeDTO) {
		return modelMapper.map(testTypeRepository.save(modelMapper.map(testTypeDTO, TestType.class)), TestTypeDTO.class);
	}
	
	public List<TestTypeDTO> getAll() {
		return testTypeRepository.findAll().stream()
				.map(t -> modelMapper.map(t, TestTypeDTO.class))
				.toList();
	}
	
	public TestTypeDTO get(long id) {
		return modelMapper.map(testTypeRepository.findById(id).orElseThrow(), TestTypeDTO.class);
	}
	
	public void delete(TestTypeDTO testTypeDTO) {
		testTypeRepository.deleteById(testTypeDTO.getId());
	}
}
