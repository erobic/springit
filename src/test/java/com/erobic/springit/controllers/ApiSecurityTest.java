package com.erobic.springit.controllers;

import com.erobic.springit.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles(value = "securedTest")
@AutoConfigureMockMvc
public class ApiSecurityTest {
    @Autowired
    MockMvc mockMvc;

    private static final String USERNAME = "erobic123";//inserted using data.sql

    @Test
    public void rootDoesNotNeedAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk());
    }

    @Test
    public void secureHomeRequiresAuthentication() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/secure_home"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USERNAME)
    public void secureHomeSucceedsWithAuthenticatedUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/secure_home"))
                .andExpect(status().isOk());
    }
}