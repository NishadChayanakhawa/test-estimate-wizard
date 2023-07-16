package io.github.nishadchayanakhawa.testestimatehub.tests;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
import io.github.nishadchayanakhawa.testestimatehub.model.dto.TestTypeDTO;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class TestTypeProcessingApiTests {
	private static final Logger logger=LoggerFactory.getLogger(TestTypeProcessingApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private static long testTypeId;
	
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
        TestTypeProcessingApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void addChangeType_test() throws Exception {
		TestTypeDTO testTypeDTO=new TestTypeDTO
				(0,"UT",0.3,10.0,0.0);
		logger.info(objectMapper.writeValueAsString(testTypeDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/config/testType")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(testTypeDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isCreated());
		logger.info(result.andReturn().getResponse().getContentAsString());
		TestTypeDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), TestTypeDTO.class);
		TestTypeProcessingApiTests.testTypeId=response.getId();
		Assertions.assertThat(response.getName()).isEqualTo("UT");
	}
	
	@Test
    @Order(1)
    void modifyChangeType_test() throws Exception {
		TestTypeDTO testTypeDTO=new TestTypeDTO
				(TestTypeProcessingApiTests.testTypeId,"UAT",0.3,10.0,0.0);
		logger.info(objectMapper.writeValueAsString(testTypeDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/config/testType")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(testTypeDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		logger.info(result.andReturn().getResponse().getContentAsString());
		TestTypeDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), TestTypeDTO.class);
		TestTypeProcessingApiTests.testTypeId=response.getId();
		Assertions.assertThat(response.getName()).isEqualTo("UAT");
	}
	
	@Test
    @Order(3)
    void getChangeType_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/config/testType/1")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		TestTypeDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), TestTypeDTO.class);
		Assertions.assertThat(response.getName()).isEqualTo("SIT");
	}
	
	@Test
    @Order(4)
    void getAllChangeTypes_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/config/testType")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		TestTypeDTO[] response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), TestTypeDTO[].class);
		Assertions.assertThat(response[0].getName()).isEqualTo("SIT");
		Assertions.assertThat(response[1].getName()).isEqualTo("UAT");
	}
	
	@Test
    @Order(5)
    void deleteChangeType_test() throws Exception {
		TestTypeDTO testTypeDTO=new TestTypeDTO();
		testTypeDTO.setId(TestTypeProcessingApiTests.testTypeId);
		ResultActions result=mvc
		.perform(
				delete(url + "/api/config/testType")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(testTypeDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
	}
}
