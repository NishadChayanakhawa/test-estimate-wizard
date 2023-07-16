package io.github.nishadchayanakhawa.testestimatehub.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import io.github.nishadchayanakhawa.testestimatehub.model.GeneralConfiguration;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.GeneralConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.jparepositories.GeneralConfigurationRepository;

@Service
public class GeneralConfigurationService {
	@Autowired
	private GeneralConfigurationRepository generalConfigurationRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public GeneralConfigurationDTO save(GeneralConfigurationDTO generalConfigurationDTO) {
		return 
			modelMapper.map
				(generalConfigurationRepository.save
						(modelMapper.map(generalConfigurationDTO,
								GeneralConfiguration.class)),
						GeneralConfigurationDTO.class);
	}
	
	public GeneralConfigurationDTO get() {
		return modelMapper.map
				(generalConfigurationRepository.findById(1L).orElseThrow(),
						GeneralConfigurationDTO.class);
	}
}
