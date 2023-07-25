package io.github.nishadchayanakhawa.testestimatehub.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import io.github.nishadchayanakhawa.testestimatehub.services.UserService;
import io.github.nishadchayanakhawa.testestimatehub.services.TestTypeService;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeTypeConfigurationService;
import io.github.nishadchayanakhawa.testestimatehub.services.GeneralConfigurationService;
import io.github.nishadchayanakhawa.testestimatehub.services.ApplicationConfigurationService;
import io.github.nishadchayanakhawa.testestimatehub.services.ReleaseService;
import io.github.nishadchayanakhawa.testestimatehub.services.ChangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.nishadchayanakhawa.testestimatehub.model.Role;
import io.github.nishadchayanakhawa.testestimatehub.model.User;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.TestTypeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeTypeConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.GeneralConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ApplicationConfigurationDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ReleaseDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.RequirementDTO;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Base64.Encoder;
import java.util.NoSuchElementException;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
	private static final Logger logger=LoggerFactory.getLogger(CommandLineAppStartupRunner.class);

	@Value("${server.port}")
	private int serverPort;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ChangeTypeConfigurationService changeTypeConfigurationService;
	
	@Autowired
	private GeneralConfigurationService generalConfigurationService;
	
	@Autowired
	private ApplicationConfigurationService applicationConfigurationService;
	
	@Autowired
	private ChangeService changeService;
	
	@Autowired
	private ReleaseService releaseService;
	
	@Autowired
	private TestTypeService testTypeService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) throws Exception {
		CommandLineAppStartupRunner.logger.info("Application started!!!");
		CommandLineAppStartupRunner.logger.info("Navigate to http://localhost:{}",serverPort);
		
		if(userService.getAllUsers().isEmpty()) {
			CommandLineAppStartupRunner.logger.warn("No users loaded. Creating default admin user");
			User user=new User();
			user.setUsername("admin");
			String generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Admin");
			user.setLastName("LNU");
			user.setEmail("admin@organization.com");
			user.setRole(Role.ADMIN);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: admin Password: {}",generatedPassword);
			
			user=new User();
			user.setUsername("tester");
			generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Tester");
			user.setLastName("LNU");
			user.setEmail("tester@organization.com");
			user.setRole(Role.TESTER);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: tester Password: {}",generatedPassword);
			
			user=new User();
			user.setUsername("testlead");
			generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Test Lead");
			user.setLastName("LNU");
			user.setEmail("testlead@organization.com");
			user.setRole(Role.TEST_LEAD);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: testlead Password: {}",generatedPassword);
			
			user=new User();
			user.setUsername("testmanager");
			generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Test Manager");
			user.setLastName("LNU");
			user.setEmail("testmanager@organization.com");
			user.setRole(Role.TEST_MANAGER);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: testmanager Password: {}",generatedPassword);
			
			user=new User();
			user.setUsername("automationmanager");
			generatedPassword=CommandLineAppStartupRunner.generateRandomPassword(20);
			user.setPassword(passwordEncoder.encode(generatedPassword));
			user.setFirstName("Automation Manager");
			user.setLastName("LNU");
			user.setEmail("automationmanager@organization.com");
			user.setRole(Role.AUTOMATION_MANAGER);
			userService.addUser(user);
			CommandLineAppStartupRunner.logger.warn("Username: automationmanager Password: {}",generatedPassword);
		}
		
		if(changeTypeConfigurationService.getAll().isEmpty()) {
			CommandLineAppStartupRunner.logger.warn("Change Type Configuration list is empty. Creating default record.");
			ChangeTypeConfigurationDTO changeTypeConfigurationDTO=new ChangeTypeConfigurationDTO
					("Significant Change",1.3,10.0,20.0,30.0);
			ChangeTypeConfigurationDTO createdCangeTypeConfigurationDTO=changeTypeConfigurationService.addOrUpdate(changeTypeConfigurationDTO);
			CommandLineAppStartupRunner.logger.warn("Test Type Configuration record created: {}",createdCangeTypeConfigurationDTO);
		}
		
		if(testTypeService.getAll().isEmpty()) {
			CommandLineAppStartupRunner.logger.warn("Test Type Configuration list is empty. Creating default record.");
			TestTypeDTO testTypeDTO=new TestTypeDTO(0,"SIT",1.0,20.0,20.0);
			TestTypeDTO createdTestTypeDTO=testTypeService.addOrUpdate(testTypeDTO);
			CommandLineAppStartupRunner.logger.warn("Test Type Configuration record created: {}",createdTestTypeDTO);
		}
		try {
			generalConfigurationService.get();
		} catch(NoSuchElementException e) {
			CommandLineAppStartupRunner.logger.warn("General Configuration is empty. Creating default record.");
			GeneralConfigurationDTO generalConfigurationDTO=new GeneralConfigurationDTO
					(1L,21,18,15,12,9,18,15,12,9,6,4,3,2,1,0.5,5,4,3,2,1,80.0,90.0,100,110,120,22.0,66.0,22.0);
			GeneralConfigurationDTO loadedGeneralConfiguration=generalConfigurationService.save(generalConfigurationDTO);
			CommandLineAppStartupRunner.logger.warn("General Configuration record created: {}",loadedGeneralConfiguration);
		}
		
		//loadTestData();
	}
	
	private void loadTestData() {
		applicationConfigurationService.add(new ApplicationConfigurationDTO(0L,"CTF","Watani I","Loan Origination",2,"LOW",null));
		applicationConfigurationService.add(new ApplicationConfigurationDTO(0L,"CTF","Watani I","Loan Stipulation",3,"MEDIUM",null));
		applicationConfigurationService.add(new ApplicationConfigurationDTO(0L,"CTF","Watani I","Loan Disbursement",4,"HIGH",null));
		applicationConfigurationService.add(new ApplicationConfigurationDTO(0L,"CTF","Watani ATR","Dashboard",5,"LOW",null));
		applicationConfigurationService.add(new ApplicationConfigurationDTO(0L,"CTF","Watani ATR","T24 Managed",6,"MEDIUM",null));
		applicationConfigurationService.add(new ApplicationConfigurationDTO(0L,"CTF","Watani ATR","Repayment",7,"HIGH",null));
		ReleaseDTO release=releaseService.addOrUpdate(new ReleaseDTO(0L,"July 2023","July 2023 Major Release",null));
		
		List<RequirementDTO> requirements=new ArrayList<>();
		RequirementDTO requirement1=new RequirementDTO
				(0,"BN01","Define first requirment","LOW",null,null);
		RequirementDTO requirement2=new RequirementDTO
				(0,"BN02","Define second requirment","HIGH",null,null);
		requirements.add(requirement1);
		requirements.add(requirement2);
		ChangeDTO changeDTO=new ChangeDTO
				(0,"Project-01","First Project","CREATED",null,release.getId(),null,null,requirements,LocalDate.now(),LocalDate.now().plusDays(5),1L,null);
		changeService.addOrUpdate(changeDTO);
	}

	private static String generateRandomPassword(int length) {
		SecureRandom random = new SecureRandom(); // Compliant for security-sensitive use cases
		byte[] bytes = new byte[length];
		random.nextBytes(bytes);
		Encoder encoder = Base64.getUrlEncoder().withoutPadding();
	    return encoder.encodeToString(bytes);
    }
	
}