package com.test.assessment;

import com.google.gson.Gson;
import com.test.assessment.controller.UserInformationController;
import com.test.assessment.dto.UserRequest;
import com.test.assessment.model.User;
import com.test.assessment.service.UserInformationServiceImpl;
import com.test.assessment.utils.DefaultServiceResponse;
import com.test.assessment.utils.ServiceResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
class AssessmentApplicationTests {



   @Mock
 protected RestTemplate restTemplate = new RestTemplate();


    private UserRequest userRequest;


    Gson gson = new Gson();


    public String BASE_URL = "http://localhost:8085/api";



    @Test
    public void testCreateUser() throws URISyntaxException {
        URI uri = new URI(BASE_URL+"/user");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        userRequest = new UserRequest();
        userRequest.setDateregistered(new Date());
        userRequest.setFirstname("test");
        userRequest.setLastname("test");
        userRequest.setEmail("lol144441@lol.com");
        userRequest.setPassword("test");
        userRequest.setMobile("0802983733");
        userRequest.setVerified("N");
        userRequest.setRoles(Collections.singleton("user"));
        userRequest.setTitle("assessment");
        LOGGER.info("Payload>>>>>" + userRequest);
        RequestEntity<UserRequest> entity = new RequestEntity<>(userRequest, headers, HttpMethod.POST, uri);
        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);
        System.out.println("RawResult>>>>>" + response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
        String responseData = response.getBody();
        LOGGER.info("ResponseData>>>>>" + responseData);
        DefaultServiceResponse userResponse = gson.fromJson(responseData, DefaultServiceResponse.class);
        LOGGER.info("Result>>>>>" + userResponse);
        Assert.assertEquals("Operation Successful", userResponse.getResponseMsg());
    }




    @Test
    public void getUsersPaginated() throws URISyntaxException {
        URI uri = new URI(BASE_URL+"/users");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        List<User> tasks = new ArrayList<>();
        Pageable pageable = PageRequest.of(1, 2);
        RequestEntity entity = new RequestEntity<>(pageable, headers, HttpMethod.GET, uri);
        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);
        System.out.println("RawResult>>>>>" + response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
        String responseData = response.getBody();
        LOGGER.info("ResponseData>>>>>" + responseData);
        DefaultServiceResponse userResponse = gson.fromJson(responseData, DefaultServiceResponse.class);
        LOGGER.info("Result>>>>>" + userResponse);
        Assert.assertEquals("Operation Successful", userResponse.getResponseMsg());

    }

    @Test
    public void deactivateUser() throws URISyntaxException {
        URI uri = new URI(BASE_URL+"/user/id/98");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity entity = new RequestEntity<>(headers, HttpMethod.DELETE, uri);
        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);
        System.out.println("RawResult>>>>>" + response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
        String responseData = response.getBody();
        LOGGER.info("ResponseData>>>>>" + responseData);
        DefaultServiceResponse userResponse = gson.fromJson(responseData, DefaultServiceResponse.class);
        LOGGER.info("Result>>>>>" + userResponse);
        Assert.assertEquals("Operation Successful", userResponse.getResponseMsg());

    }

    @Test
    public void updateUser() throws URISyntaxException {
        URI uri = new URI(BASE_URL+"/user/id/98");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        userRequest.setFirstname("test");
        userRequest.setLastname("test");
        userRequest.setEmail("lol144441@lol.com");
        userRequest.setPassword("test");
        RequestEntity entity = new RequestEntity<>(userRequest,headers, HttpMethod.PUT, uri);
        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);
        System.out.println("RawResult>>>>>" + response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
        String responseData = response.getBody();
        LOGGER.info("ResponseData>>>>>" + responseData);
        DefaultServiceResponse userResponse = gson.fromJson(responseData, DefaultServiceResponse.class);
        LOGGER.info("Result>>>>>" + userResponse);
        Assert.assertEquals("Operation Successful", userResponse.getResponseMsg());

    }

    @Test
    public void verifyUser() throws URISyntaxException {
        URI uri = new URI(BASE_URL+"/verify?email=lol144441@lol.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        RequestEntity entity = new RequestEntity<>(headers, HttpMethod.GET, uri);
        ResponseEntity<String> response = restTemplate.exchange(entity, String.class);
        System.out.println("RawResult>>>>>" + response);
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
        Assert.assertNotNull(response.getBody());
        String responseData = response.getBody();
        LOGGER.info("ResponseData>>>>>" + responseData);
        DefaultServiceResponse userResponse = gson.fromJson(responseData, DefaultServiceResponse.class);
        LOGGER.info("Result>>>>>" + userResponse);
        Assert.assertEquals("Operation Successful", userResponse.getResponseMsg());

    }


}