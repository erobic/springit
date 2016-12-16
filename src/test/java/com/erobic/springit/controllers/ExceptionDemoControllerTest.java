package com.erobic.springit.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ExceptionDemoController.class)
public class ExceptionDemoControllerTest {

    @Autowired
    MockMvc mvc;

    @Test
    public void testNotFound() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exceptions/not_found"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCustom() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/exceptions/custom"))
                .andExpect(status().isInternalServerError());
    }

}