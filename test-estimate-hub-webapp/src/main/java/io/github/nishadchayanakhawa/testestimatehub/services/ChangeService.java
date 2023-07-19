package io.github.nishadchayanakhawa.testestimatehub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nishadchayanakhawa.testestimatehub.jparepositories.ChangeRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.Change;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;

@Service
public class ChangeService {
	@Autowired
	private ChangeRepository changeRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ChangeDTO addOrUpdate(ChangeDTO changeDTO) {
		return 
				modelMapper.map
					(changeRepository.save
							(modelMapper.map(changeDTO, Change.class)), ChangeDTO.class);
	}
	
	public ChangeDTO get(Long id) {
		return modelMapper.map(changeRepository.findById(id).orElseThrow(), ChangeDTO.class);
	}
	
	public void delete(ChangeDTO changeDTO) {
		changeRepository.deleteById(changeDTO.getId());
	}
	
	public List<ChangeDTO> getAll() {
		return changeRepository.findAll().stream()
					.map(c-> modelMapper.map(c, ChangeDTO.class))
					.toList();
	}
	
	public boolean exists(ChangeDTO changeDTO) {
		return changeRepository.existsById(changeDTO.getId());
	}
}
