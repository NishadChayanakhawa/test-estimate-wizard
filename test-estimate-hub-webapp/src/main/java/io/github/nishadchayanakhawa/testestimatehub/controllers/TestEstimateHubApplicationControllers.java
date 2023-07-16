package io.github.nishadchayanakhawa.testestimatehub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.GeneralConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.services.GeneralConfigurationService;

@Controller
public class TestEstimateHubApplicationControllers {
	@Autowired
	private GeneralConfigurationService generalConfigurationService;
	
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
	
	@GetMapping("/configuration/general")
	public String getGeneralConfigurationPage(Model model) {
		GeneralConfigurationDTO generalConfiguration=generalConfigurationService.get();
		model.addAttribute("generalConfiguration", generalConfiguration);
		return "configuration/general";
	}
}
