package io.github.nishadchayanakhawa.testestimatehub.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nishadchayanakhawa.testestimatehub.TestEstimateHubApplication;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeTypeConfigurationDTO;
import org.springframework.test.web.servlet.ResultActions;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class ChangeTypeConfigurationApiTests {
private static final Logger logger=LoggerFactory.getLogger(ChangeTypeConfigurationApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private static long changeTypeId;
	
	private String url;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
    private WebApplicationContext context;
	
	@Autowired
	ModelMapper modelMapper;
	
	private MockMvc mvc;
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
        url=String.format("http://localhost:%d", serverPort);
        ChangeTypeConfigurationApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void addChangeType_test() throws Exception {
		ChangeTypeConfigurationDTO changeTypeConfigurationDTO=new ChangeTypeConfigurationDTO
				("Significant Change",12.1,20,20,30);
		logger.info(objectMapper.writeValueAsString(changeTypeConfigurationDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/config/changeType")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(changeTypeConfigurationDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isCreated());
		ChangeTypeConfigurationDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeTypeConfigurationDTO.class);
		ChangeTypeConfigurationApiTests.changeTypeId=response.getId();
		Assertions.assertThat(response.getName()).isEqualTo("Significant Change");
	}
	
	@Test
    @Order(2)
    void updateChangeType_test() throws Exception {
		ChangeTypeConfigurationDTO changeTypeConfigurationDTO=new ChangeTypeConfigurationDTO
				("Significant Change Modified",12.1,20,20,30);
		changeTypeConfigurationDTO.setId(ChangeTypeConfigurationApiTests.changeTypeId);
		logger.info(objectMapper.writeValueAsString(changeTypeConfigurationDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/config/changeType")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(changeTypeConfigurationDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		ChangeTypeConfigurationDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeTypeConfigurationDTO.class);
		Assertions.assertThat(response.getName()).isEqualTo("Significant Change Modified");
	}
	
	@Test
    @Order(3)
    void getChangeType_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/config/changeType/" + ChangeTypeConfigurationApiTests.changeTypeId)
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		ChangeTypeConfigurationDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeTypeConfigurationDTO.class);
		Assertions.assertThat(response.getName()).isEqualTo("Significant Change Modified");
	}
	
	@Test
    @Order(4)
    void getAllChangeTypes_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/config/changeType")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		ChangeTypeConfigurationDTO[] response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeTypeConfigurationDTO[].class);
		Assertions.assertThat(response[0].getName()).isEqualTo("Significant Change Modified");
	}
	
	@Test
    @Order(5)
    void deleteChangeType_test() throws Exception {
		ChangeTypeConfigurationDTO changeTypeConfigurationDTO=new ChangeTypeConfigurationDTO();
		changeTypeConfigurationDTO.setId(ChangeTypeConfigurationApiTests.changeTypeId);
		logger.info(objectMapper.writeValueAsString(changeTypeConfigurationDTO));
		ResultActions result=mvc
		.perform(
				delete(url + "/api/config/changeType")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(changeTypeConfigurationDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
	}
}
