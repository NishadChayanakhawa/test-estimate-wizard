package io.github.nishadchayanakhawa.testestimatehub.services;

import java.util.List;
import java.util.ArrayList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nishadchayanakhawa.testestimatehub.jparepositories.ChangeRepository;
import io.github.nishadchayanakhawa.testestimatehub.jparepositories.UseCaseRepository;
import io.github.nishadchayanakhawa.testestimatehub.model.Change;
import io.github.nishadchayanakhawa.testestimatehub.model.UseCase;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.RequirementDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.UseCaseDTO;
import java.util.Map;
import java.util.HashMap;
@Service
public class ChangeService {
	@Autowired
	private ChangeRepository changeRepository;
	
	@Autowired
	private UseCaseRepository useCaseRepository;
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	ObjectMapper objectMapper;
	
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
	
	public ChangeDTO saveUseCases(List<UseCaseDTO> useCasesDTO) throws JsonProcessingException {
		return saveUseCases(useCasesDTO,false);
	}
	
	public ChangeDTO submitEstimationForReview(List<UseCaseDTO> useCasesDTO) throws JsonProcessingException {
		return saveUseCases(useCasesDTO,true);
	}
	
	private ChangeDTO saveUseCases(List<UseCaseDTO> useCasesDTO,boolean isSubmittedForReview) throws JsonProcessingException {
		if(useCasesDTO.isEmpty()) {
			return null;
		}
		
		List<UseCaseDTO> enrichedUseCasesDTO=useCasesDTO.stream()
				.map(u -> modelMapper.map(modelMapper.map(u, UseCase.class), UseCaseDTO.class))
				.toList();
		
		ChangeDTO change=get(enrichedUseCasesDTO.get(0).getChangeId());
		
		Map<Long,List<UseCaseDTO>> useCaseByRequirementMap=new HashMap<>();
		List<Long> validUseCaseIdList=new ArrayList<>();
		
		for(UseCaseDTO useCaseDTO : enrichedUseCasesDTO) {
			if(useCaseDTO.getId()>0) {
				validUseCaseIdList.add(useCaseDTO.getId());
			}
			if(!useCaseByRequirementMap.containsKey(useCaseDTO.getRequirementId())) {
				useCaseByRequirementMap.put(useCaseDTO.getRequirementId(), new ArrayList<>());
			}
			useCaseByRequirementMap.get(useCaseDTO.getRequirementId()).add(useCaseDTO);
		}
		
		List<Long> invalidUseCaseIdList=new ArrayList<>();
		for(RequirementDTO requirementDTO : change.getRequirements()) {
			for(UseCaseDTO useCaseDTO : requirementDTO.getUseCases()) {
				if(!validUseCaseIdList.contains(useCaseDTO.getId())) {
					invalidUseCaseIdList.add(useCaseDTO.getId());
				}
			}
		}
		for(int iRequirementCounter=0;iRequirementCounter<change.getRequirements().size();iRequirementCounter++) {
			change.getRequirements().get(iRequirementCounter)
				.setUseCases(useCaseByRequirementMap.get(change.getRequirements().get(iRequirementCounter).getId()));
		}
		
		if(isSubmittedForReview) {
			change.setStatusCode("ESTIMATED");
		}
		ChangeDTO updatedChange=modelMapper.map(changeRepository.save(modelMapper.map(change, Change.class)), ChangeDTO.class);
		
		for(Long deletedId : invalidUseCaseIdList) {
			useCaseRepository.deleteById(deletedId);
		}
		
		return updatedChange;
	}
}
