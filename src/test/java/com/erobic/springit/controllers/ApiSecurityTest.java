package com.erobic.springit.controllers;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.TestApplication;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ActiveProfiles(value = "securedTest")
@AutoConfigureMockMvc
public class ApiSecurityTest extends AbstractSpringTest {
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