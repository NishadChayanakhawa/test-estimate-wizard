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
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import java.util.List;
import java.util.ArrayList;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.nishadchayanakhawa.testestimatehub.TestEstimateHubApplication;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ChangeDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.ReleaseDTO;
import io.github.nishadchayanakhawa.testestimatehub.model.dto.RequirementDTO;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChangeProcessingApiTests {
private static final Logger logger=LoggerFactory.getLogger(ChangeProcessingApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private String url;
	
	private static Long releaseId;
	private static Long changeId;
	private static ChangeDTO created;
	
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
        ChangeProcessingApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void addReleaseType_test() throws Exception {
		ReleaseDTO releaseDTO=new ReleaseDTO
				(0,"AUGUST-2023","August 2023 Major Release",null);
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
		releaseId=response.getId();
		Assertions.assertThat(response.getName()).isEqualTo("August 2023 Major Release");
	}
	
	@Test
    @Order(2)
    void addChangeType_test() throws Exception {
		List<RequirementDTO> requirements=new ArrayList<>();
		RequirementDTO requirement1=new RequirementDTO
				(0,"BN01","Define first requirment","LOW",null);
		RequirementDTO requirement2=new RequirementDTO
				(0,"BN02","Define second requirment","HIGH",null);
		requirements.add(requirement1);
		requirements.add(requirement2);
		ChangeDTO changeDTO=new ChangeDTO
				(0,"Project-01","First Project","CREATED",null,releaseId,null,null,requirements);
		logger.info(objectMapper.writeValueAsString(changeDTO));
		ResultActions result=mvc
		.perform(
				put(url + "/api/change")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(changeDTO))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isCreated());
		logger.info(result.andReturn().getResponse().getContentAsString());
		ChangeDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeDTO.class);
		changeId=response.getId();
		Assertions.assertThat(response.getName()).isEqualTo("First Project");
	}
	
	@Test
    @Order(3)
    void getChangeType_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/change/" + changeId)
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		logger.info(result.andReturn().getResponse().getContentAsString());
		created=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeDTO.class);
		Assertions.assertThat(created.getName()).isEqualTo("First Project");
	}
	
	@Test
    @Order(3)
    void getAllChangeType_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/change")
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		logger.info(result.andReturn().getResponse().getContentAsString());
		ChangeDTO[] response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeDTO[].class);
		Assertions.assertThat(response[0].getName()).isEqualTo("First Project");
	}
	
	@Test
    @Order(4)
    void updateChangeType_test() throws Exception {
		List<RequirementDTO> requirements=created.getRequirements();
		RequirementDTO requirement3=new RequirementDTO
				(0,"BN03","Add third requirment","VERY_LOW",null);
		RequirementDTO requirement4=new RequirementDTO
				(0,"BN04","Add fourth requirment","VERY_HIGH",null);
		requirements.add(requirement3);
		requirements.add(requirement4);
		created.setRequirements(requirements);
		
		logger.info(objectMapper.writeValueAsString(created));
		ResultActions result=mvc
		.perform(
				put(url + "/api/change")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(created))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		logger.info(result.andReturn().getResponse().getContentAsString());
		ChangeDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ChangeDTO.class);
		changeId=response.getId();
		Assertions.assertThat(response.getRequirements().get(3).getIdentifier()).containsAnyOf("BN01","BN02","BN03","BN04");
	}
	
	@Test
    @Order(5)
    void getRelease_test() throws Exception {
		ResultActions result=mvc
		.perform(
				get(url + "/api/release/" + releaseId)
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
		logger.info(result.andReturn().getResponse().getContentAsString());
		ReleaseDTO response=objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), ReleaseDTO.class);
		Assertions.assertThat(response.getChanges().get(0).getName()).isEqualTo("First Project");
	}
	
	@Test
    @Order(6)
    void deleteChangeType_test() throws Exception {
		ChangeDTO request=new ChangeDTO();
		request.setId(changeId);
		ResultActions result=mvc
		.perform(
				delete(url + "/api/change")
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(objectMapper.writeValueAsString(request))
				.with(user("admin").password("admin").roles("ADMIN")));
		result.andExpect(status().isOk());
	}
}
