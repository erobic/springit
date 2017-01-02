package com.erobic.springit.web.controllers;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.JacksonDemoEntity;
import com.erobic.springit.repositories.JacksonDemoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JacksonDemoControllerTest extends AbstractSpringTest{

    @Autowired
    MockMvc mvc;
    @MockBean
    JacksonDemoRepository repository;

    @Test
    public void testGet_shouldReturnOk() throws Exception {
        //given
        Long id = 11l;
        JacksonDemoEntity jacksonDemoEntity = DataGenerator.generateJacksonExperiment();
        given(repository.findOne(id)).willReturn(jacksonDemoEntity);
        //when and then
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/jackson/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hidden").doesNotExist())
                .andExpect(jsonPath("$.createdOn").exists())
                .andReturn();
    }

}