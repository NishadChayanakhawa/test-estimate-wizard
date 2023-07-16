package io.github.nishadchayanakhawa.testestimatehub.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.api.Assertions;
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
import io.github.nishadchayanakhawa.testestimatehub.model.dto.GeneralConfigurationDTO;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class GeneralConfigurationApiTests {
private static final Logger logger=LoggerFactory.getLogger(GeneralConfigurationApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private String url;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	ModelMapper modelMapper;
	
	private MockMvc mvc;
	
	private static GeneralConfigurationDTO response;
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
        url=String.format("http://localhost:%d", serverPort);
        GeneralConfigurationApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void getChangeType_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/config/general")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		logger.info(result.andReturn().getResponse().getContentAsString());
		response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), GeneralConfigurationDTO.class);
		Assertions.assertThat(response.getAutomationNewDesignProductivityForMediumComplexity()).isEqualTo(2.0d);
	}
	
	@Test
    @Order(2)
    void modifyChangeType_test() throws Exception {
		response.setAutomationMaintenanceProductivityForMediumComplexity(99.0d);
		ResultActions result=mvc
		.perform(
				put(url + "/api/config/general")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(response))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), GeneralConfigurationDTO.class);
		Assertions.assertThat(response.getAutomationMaintenanceProductivityForMediumComplexity()).isEqualTo(99.0d);
	}
}
