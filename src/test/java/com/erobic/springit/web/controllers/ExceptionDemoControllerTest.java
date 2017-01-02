package com.erobic.springit.web.controllers;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.web.controllers.ExceptionDemoController;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ExceptionDemoController.class)
public class ExceptionDemoControllerTest extends AbstractSpringTest {

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