package com.erobic.springit.controllers;

import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.JacksonExperiment;
import com.erobic.springit.repositories.JacksonExperimentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.isNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class JacksonExperimentControllerTest {

    @Autowired
    MockMvc mvc;
    @MockBean
    JacksonExperimentRepository repository;

    @Test
    public void testGet_shouldReturnOk() throws Exception {
        //given
        Long id = 11l;
        JacksonExperiment jacksonExperiment = DataGenerator.generateJacksonExperiment();
        given(repository.findOne(id)).willReturn(jacksonExperiment);
        //when and then
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/jackson/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.hidden").doesNotExist())
                .andExpect(jsonPath("$.createdOn").exists())
                .andReturn();
    }

}