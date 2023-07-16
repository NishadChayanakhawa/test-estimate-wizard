package io.github.nishadchayanakhawa.testestimatehub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nishadchayanakhawa.testestimatehub.jparepositories.ChangeTypeConfigurationRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.ChangeTypeConfiguration;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeTypeConfigurationDTO;
@Service
public class ChangeTypeConfigurationService {			
	@Autowired
	private ChangeTypeConfigurationRepository changeTypeConfigurationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public boolean exists(ChangeTypeConfigurationDTO changeTypeConfigurationDTO) {
		return changeTypeConfigurationRepository.existsById(changeTypeConfigurationDTO.getId());
	}
	
	public ChangeTypeConfigurationDTO addOrUpdate(ChangeTypeConfigurationDTO changeTypeConfigurationDTO) {
		return modelMapper.map(
				changeTypeConfigurationRepository.save(
						modelMapper.map(
								changeTypeConfigurationDTO, 
								ChangeTypeConfiguration.class)), 
				ChangeTypeConfigurationDTO.class);
	}
	
	public ChangeTypeConfigurationDTO get(Long id) {
		return modelMapper.map(changeTypeConfigurationRepository.findById(id), ChangeTypeConfigurationDTO.class);
	}
	
	public void delete(ChangeTypeConfigurationDTO changeTypeConfigurationDTO) {
		changeTypeConfigurationRepository.deleteById(changeTypeConfigurationDTO.getId());
	}
	
	public List<ChangeTypeConfigurationDTO> getAll() {
		return changeTypeConfigurationRepository.findAll().stream()
				.map(c -> modelMapper.map(c, ChangeTypeConfigurationDTO.class))
				.toList();
	}
}
