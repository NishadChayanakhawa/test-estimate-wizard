package io.github.nishadchayanakhawa.testestimatehub.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestEstimateHubApplicationControllers {
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
	
	@GetMapping("/home")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/setting/usermanagement")
	public String getUserManagementPage() {
		return "setting/userManagement";
	}
}
