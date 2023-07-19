package io.github.nishadchayanakhawa.testestimatehub.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.github.nishadchayanakhawa.testestimatehub.model.dto.GeneralConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeTypeConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ReleaseDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.GeneralConfigurationService;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeTypeConfigurationService;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeService;
import io.github.nishadchayanakhawa.testestimatehub.services.ReleaseService;

@Controller
public class TestEstimateHubApplicationControllers {
	@Autowired
	private GeneralConfigurationService generalConfigurationService;
	
	@Autowired
	private ChangeTypeConfigurationService changeTypeConfigurationService;
	
	@Autowired
	private ChangeService changeService;
	
	@Autowired
	private ReleaseService releaseService;
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/configuration/usermanagement")
	public String getUserManagementPage() {
		return "configuration/userManagement";
	}
	
	@GetMapping("/configuration/application")
	public String getApplicationConfigurationPage() {
		return "configuration/application";
	}
	
	@GetMapping("/configuration/changeType")
	public String getChangeTypeConfigurationPage() {
		return "configuration/changeType";
	}
	
	@GetMapping("/configuration/testType")
	public String getTestTypeConfigurationPage() {
		return "configuration/testType";
	}
	
	@GetMapping("/record/release")
	public String getReleaseManagementPage() {
		return "record/release";
	}
	
	@GetMapping("/record/change")
	public String getChangeManagementPage(Model model) {
		List<ChangeTypeConfigurationDTO> changeTypes=changeTypeConfigurationService.getAll();
		model.addAttribute("changeTypes", changeTypes);
		
		List<ReleaseDTO> releases=releaseService.getAll();
		model.addAttribute("releases", releases);
		
		return "record/change";
	}
	
	@GetMapping("/configuration/general")
	public String getGeneralConfigurationPage(Model model) {
		GeneralConfigurationDTO generalConfiguration=generalConfigurationService.get();
		model.addAttribute("generalConfiguration", generalConfiguration);
		return "configuration/general";
	}
	
	@GetMapping("/estimation/estimate/{id}")
	public String getSubmitEstimationPage(@PathVariable Long id,Model model) {
		ChangeDTO change=changeService.get(id);
		model.addAttribute("change", change);
		return "estimation/estimate";
	}
}
