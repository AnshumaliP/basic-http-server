package com.example.basichttpserver.controller;

import com.example.basichttpserver.model.Greeting;
import com.example.basichttpserver.service.GreetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GreetingController.class)
public class GreetingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GreetingService greetingService;

    @BeforeEach
    public void setup() {
        // Mocking the service methods
        given(greetingService.createGreeting(ArgumentMatchers.anyString()))
                .willAnswer(invocation -> new Greeting(1L, "Hello, " + invocation.getArgument(0) + "!"));
        given(greetingService.getGreeting(1L))
                .willReturn(new Greeting(1L, "Hello, TestUser!"));
        given(greetingService.updateGreeting(ArgumentMatchers.eq(1L), ArgumentMatchers.anyString()))
                .willAnswer(invocation -> new Greeting(1L, "Hello, " + invocation.getArgument(1) + "!"));
        given(greetingService.getAllGreetings())
                .willReturn(new ConcurrentHashMap<>(Collections.singletonMap(1L, new Greeting(1L, "Hello, TestUser!"))));
    }

    @Test
    public void testCreateGreeting() throws Exception {
        mockMvc.perform(post("/greeting?name=TestUser")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, TestUser!")));
    }

    @Test
    public void testGetGreeting() throws Exception {
        mockMvc.perform(get("/greeting/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, TestUser!")));
    }

    @Test
    public void testUpdateGreeting() throws Exception {
        mockMvc.perform(put("/greeting/1?name=TestUserUpdated")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, TestUserUpdated!")));
    }

    @Test
    public void testDeleteGreeting() throws Exception {
        mockMvc.perform(delete("/greeting/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        // Additional assertions can be added to verify the delete operation
    }

    @Test
    public void testGetAllGreetings() throws Exception {
        mockMvc.perform(get("/greetings")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, TestUser!")));
    }
}
