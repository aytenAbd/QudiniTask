package com.qudini.task.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.*;

@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest {
    @Autowired
    MockMvc mvc;

    @Test
    public void testCustomersPostOk() throws Exception {
        String input = "[{\"id\":123,\"name\":\"Ulysses Leon\",\"duetime\":\"2014-06-18T06:26:56-07:00\",\"jointime\":\"2015-04-08T12:47:16-07:00\"}]";
        String output = "[{\"id\":123,\"name\":\"Ulysses Leon\",\"duetime\":\"2014-06-18T13:26:56Z\",\"jointime\":\"2015-04-08T19:47:16Z\"}]";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input);
        ResultActions action = mvc.perform(request);
        MockHttpServletResponse mvcResponse = action.andReturn().getResponse();
        assertEquals(mvcResponse.getStatus(), HttpStatus.OK.value());
        assertEquals(mvcResponse.getContentAsString(),output);
    }

    @Test
    public void testMandatoryFieldMissedError() throws Exception {
        String input = "[{\"name\":\"Ulysses Leon\",\"jointime\":\"2015-04-08T19:47:16Z\"}]";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input);
        ResultActions action = mvc.perform(request);
        MockHttpServletResponse response = action.andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
        assertTrue( response.getContentAsString().contains("Failed input param validation for fields"));
        assertTrue( response.getContentAsString().contains("ID field must not be null"));
        assertTrue( response.getContentAsString().contains("Duetime field must not be null"));
    }

    @Test
    public void testJsonParseException() throws Exception {
        String input = "[{\"id\":\"12s\",\"name\":\"Ulysses Leon\",\"duetime\":\"2014-06-18T13:26:56Z\",\"jointime\":\"2015-04-08T19:47:16Z\"}]";
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(input);
        ResultActions action = mvc.perform(request);
        MockHttpServletResponse response = action.andReturn().getResponse();
        assertEquals(response.getStatus(), HttpStatus.BAD_REQUEST.value());
        assertTrue(response.getContentAsString().contains("JSON parse error"));
    }

}