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
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ReleaseDTO;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class ReleaseProcessingApiTests {
	private static final Logger logger=LoggerFactory.getLogger(ReleaseProcessingApiTests.class);
	
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
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
        url=String.format("http://localhost:%d", serverPort);
        ReleaseProcessingApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void addChangeType_test() throws Exception {
		ReleaseDTO releaseDTO=new ReleaseDTO
				("JULY-2023","July 2023 Major Release");
		logger.info(objectMapper.writeValueAsString(releaseDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/release")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(releaseDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isCreated());
		logger.info(result.andReturn().getResponse().getContentAsString());
		ReleaseDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReleaseDTO.class);
		Assertions.assertThat(response.getName()).isEqualTo("July 2023 Major Release");
	}
	
	@Test
    @Order(1)
    void modifyChangeType_test() throws Exception {
		ReleaseDTO releaseDTO=new ReleaseDTO
				("JULY-2023","July 2023 Minor Release");
		ResultActions result=mvc
		.perform(
				put(url + "/api/release")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(releaseDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		ReleaseDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReleaseDTO.class);
		Assertions.assertThat(response.getName()).isEqualTo("July 2023 Minor Release");
	}
	
	@Test
    @Order(3)
    void getChangeType_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/release/JULY-2023")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		ReleaseDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReleaseDTO.class);
		Assertions.assertThat(response.getName()).isEqualTo("July 2023 Minor Release");
	}
	
	@Test
    @Order(4)
    void getAllChangeTypes_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/release")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		ReleaseDTO[] response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReleaseDTO[].class);
		Assertions.assertThat(response[0].getName()).isEqualTo("July 2023 Minor Release");
	}
	
	@Test
    @Order(5)
    void deleteChangeType_test() throws Exception {
		ReleaseDTO releaseDTO=new ReleaseDTO();
		releaseDTO.setId("JULY-2023");
		ResultActions result=mvc
		.perform(
				delete(url + "/api/release")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(releaseDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
	}
}
