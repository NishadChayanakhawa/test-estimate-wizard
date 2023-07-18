package io.github.nishadchayanakhawa.testestimatehub.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.github.nishadchayanakhawa.testestimatehub.jparepositories.ReleaseRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.Release;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ReleaseDTO;

@Service
public class ReleaseService {
	@Autowired
	private ReleaseRepository releaseRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	public ReleaseDTO addOrUpdate(ReleaseDTO releaseDTO) {
		return 
				modelMapper.map
					(releaseRepository.save
							(modelMapper.map(releaseDTO, Release.class)), ReleaseDTO.class);
	}
	
	public ReleaseDTO get(String id) {
		return modelMapper.map(releaseRepository.findById(id).orElseThrow(), ReleaseDTO.class);
	}
	
	public void delete(ReleaseDTO releaseDTO) {
		releaseRepository.deleteById(releaseDTO.getId());
	}
	
	public List<ReleaseDTO> getAll() {
		return releaseRepository.findAll().stream()
					.map(r-> modelMapper.map(r, ReleaseDTO.class))
					.toList();
	}
	
	public boolean exists(ReleaseDTO releaseDTO) {
		return releaseRepository.existsById(releaseDTO.getId());
	}
}
