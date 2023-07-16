package io.github.nishadchayanakhawa.testestimatehub.services;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.nishadchayanakhawa.testestimatehub.jparepositories.ApplicationConfigurationRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.ApplicationConfiguration;
import io.github.nishadchayanakhawa.testestimatehub.model.ApplicationConfigurationID;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ApplicationConfigurationDTO;

@Service
public class ApplicationConfigurationService {		
	@Autowired
	private ApplicationConfigurationRepository applicationConfigurationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public List<ApplicationConfigurationDTO> getAll() {
		return applicationConfigurationRepository.findAll().stream()
				.map(applicationConfiguration -> modelMapper.map(applicationConfiguration, ApplicationConfigurationDTO.class))
				.toList();
	}
	
	public ApplicationConfigurationDTO add(ApplicationConfigurationDTO applicationConfigurationDTO) {
		return modelMapper.map(applicationConfigurationRepository.save(modelMapper.map(applicationConfigurationDTO, ApplicationConfiguration.class)), ApplicationConfigurationDTO.class);
	}
	
	public boolean exists(ApplicationConfigurationDTO applicationConfigurationDTO) {
		ApplicationConfigurationID applicationConfigurationId=
				modelMapper.map(applicationConfigurationDTO, ApplicationConfiguration.class).getApplicationConfigurationId();
		return applicationConfigurationRepository.existsById(applicationConfigurationId);
	}
	
	public ApplicationConfigurationDTO get(ApplicationConfigurationDTO applicationConfigurationDTO) {
		ApplicationConfigurationID applicationConfigurationId=
				modelMapper.map(applicationConfigurationDTO, ApplicationConfiguration.class).getApplicationConfigurationId();
		return modelMapper.map(applicationConfigurationRepository.findById(applicationConfigurationId).orElseThrow(), ApplicationConfigurationDTO.class);
	}
	
	public void delete(ApplicationConfigurationDTO applicationConfigurationDTO) {
		ApplicationConfigurationID applicationConfigurationId=
				modelMapper.map(applicationConfigurationDTO, ApplicationConfiguration.class).getApplicationConfigurationId();
		applicationConfigurationRepository.deleteById(applicationConfigurationId);
	}
}
