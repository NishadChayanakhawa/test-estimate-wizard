package io.github.nishadchayanakhawa.testestimatehub.tests;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import io.github.nishadchayanakhawa.testestimatehub.model.Role;
import io.github.nishadchayanakhawa.testestimatehub.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.nishadchayanakhawa.testestimatehub.TestEstimateHubApplication;
import org.slf4j.Logger;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@TestMethodOrder(OrderAnnotation.class)
@SpringBootTest(classes = TestEstimateHubApplication.class,webEnvironment=SpringBootTest.WebEnvironment.DEFINED_PORT)
class UserProcessingApiTests {
	private static final Logger logger=LoggerFactory.getLogger(UserProcessingApiTests.class);
	
	@Value("${server.port}")
	private int serverPort;
	
	private String url;
	
	@Autowired
    private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@BeforeEach
    public void setup() {
        mvc = MockMvcBuilders
          .webAppContextSetup(context)
          .build();
        url=String.format("http://localhost:%d", serverPort);
        UserProcessingApiTests.logger.info("{}",url);
    }
	
	@Test
    @Order(1)
    void addUser_test() throws Exception {
		User user=new User();
		user.setUsername("test");
		user.setPassword("test");
		user.setRole(Role.TESTER);
    			mvc
    		.perform(
    				put(url + "/api/user")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(user))
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isCreated()).andReturn();
    	UserProcessingApiTests.logger.info("addUser_test [PASS]");
    }
	
	@Test
    @Order(1)
    void addAnotherUser_test() throws Exception {
		User user=new User();
		user.setUsername("test1");
		user.setPassword("test");
		user.setRole(Role.TESTER);
    			mvc
    		.perform(
    				put(url + "/api/user")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(user))
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isCreated()).andReturn();
    	UserProcessingApiTests.logger.info("addUser_test [PASS]");
    }
	
	@Test
    @Order(2)
    void updateUser_test() throws Exception {
		User user=new User();
		user.setUsername("test");
		user.setFirstName("Jane");
		user.setRole(Role.TESTER);
		UserProcessingApiTests.logger.info("{}",objectMapper.writeValueAsString(user));
    			mvc
    		.perform(
    				put(url + "/api/user")
    				.contentType(MediaType.APPLICATION_JSON_VALUE)
    				.content(objectMapper.writeValueAsString(user))
    				.with(user("admin").password("admin").roles("ADMIN")))
    		.andExpect(status().isOk()).andReturn();
    	UserProcessingApiTests.logger.info("updateUser_test [PASS]");
    }
	
	@Test
    @Order(3)
    void getAllUsers_test() throws Exception {
    	MvcResult result=
    			mvc
    		.perform(
    				get(url + "/api/user")
    				.with(user("admin").password("admin").roles(Role.ADMIN.toString())))
    		.andExpect(status().isOk()).andReturn();
    	UserProcessingApiTests.logger.info("{}",result.getResponse().getContentAsString());
    	UserProcessingApiTests.logger.info("getAllUsers_test [PASS]");
    }
	
	@Test
    @Order(4)
    void getUser_test() throws Exception {
    	MvcResult result=
    			mvc
    		.perform(
    				get(url + "/api/user/test")
    				.with(user("admin").password("admin").roles(Role.ADMIN.toString())))
    		.andExpect(status().isOk()).andReturn();
    	UserProcessingApiTests.logger.info("{}",result.getResponse().getContentAsString());
    	UserProcessingApiTests.logger.info("getUser_test [PASS]");
    }
	
	@Test
    @Order(5)
    void deleteUser_test() throws Exception {
    			mvc
    		.perform(
    				delete(url + "/api/user/test1")
    				.with(user("admin").password("admin").roles(Role.ADMIN.toString())))
    		.andExpect(status().isOk()).andReturn();
    	UserProcessingApiTests.logger.info("deleteUser_test [PASS]");
    }
}
