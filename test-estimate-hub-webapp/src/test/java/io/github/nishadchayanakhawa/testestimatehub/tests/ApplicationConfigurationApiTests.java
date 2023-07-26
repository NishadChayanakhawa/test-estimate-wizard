package io.github.nishadchayanakhawa.testestimatehub.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nishadchayanakhawa.testestimatehub.TestEstimateHubApplication;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ApplicationConfigurationDTO;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class ApplicationConfigurationApiTests {
	private static final Logger logger=LoggerFactory.getLogger(ApplicationConfigurationApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private String url;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	ModelMapper modelMapper;
	
	private static Long createdId;
	
	private MockMvc mvc;
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
        url=String.format("http://localhost:%d", serverPort);
        ApplicationConfigurationApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void addApplicationConfig_test() throws Exception {
		ApplicationConfigurationDTO applicationConfigurationDTO=new ApplicationConfigurationDTO
				(0L,"App1","Module1","SubModule1",3.4,"MEDIUM",null);
		logger.info(objectMapper.writeValueAsString(applicationConfigurationDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/config/application")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(applicationConfigurationDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isCreated()).andReturn();
		ApplicationConfigurationDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ApplicationConfigurationDTO.class);
		createdId=response.getId();
	}
	
	@Test
    @Order(2)
    void updateApplicationConfig_test() throws Exception {
		ApplicationConfigurationDTO applicationConfigurationDTO=new ApplicationConfigurationDTO
				(createdId,"App1","Module1","SubModule1",3.4,"HIGH",null);
		mvc
		.perform(
				put(url + "/api/config/application")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(applicationConfigurationDTO))
				.with(user("admin").password("admin").roles("ADMIN")))
		.andExpect(status().isOk()).andReturn();
	}
	
	@Test
    @Order(3)
    void getApplicationConfigs_test() throws Exception {
		mvc
		.perform(
				get(url + "/api/config/application")
				.with(user("admin").password("admin").roles("ADMIN")))
		.andExpect(status().isOk()).andReturn();
	}
	
	@Test
    @Order(4)
    void getApplicationConfig_test() throws Exception {
		mvc
		.perform(
				get(url + "/api/config/application/" + createdId)
				.with(user("admin").password("admin").roles("ADMIN")))
		.andExpect(status().isOk()).andReturn();
	}
	
	@Test
    @Order(5)
    void deleteApplicationConfig_test() throws Exception {
		ApplicationConfigurationDTO applicationConfigurationDTO=new ApplicationConfigurationDTO();
		applicationConfigurationDTO.setId(createdId);
		mvc
		.perform(
				delete(url + "/api/config/application")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(applicationConfigurationDTO))
				.with(user("admin").password("admin").roles("ADMIN")))
		.andExpect(status().isOk()).andReturn();
	}
}
