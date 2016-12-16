package com.erobic.springit.controllers;

import com.erobic.springit.dtos.CreatedResponse;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.models.JacksonExperiment;
import com.erobic.springit.repositories.JacksonExperimentRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URL;
import java.util.Map;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JacksonExperimentControllerIT {
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;
    @Autowired
    JacksonExperimentRepository jacksonExperimentRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/jackson");
    }

    @Test
    public void testGet_ignoredFieldShouldBeNull() throws Exception {
        //given
        JacksonExperiment obj1 = DataGenerator.generateJacksonExperiment();
        obj1 = jacksonExperimentRepository.saveAndFlush(obj1);
        //when
        ResponseEntity<JacksonExperiment> response = template.getForEntity(base.toString() + "/" + obj1.getId(), JacksonExperiment.class);
        JacksonExperiment afterGet = response.getBody();
        //then
        assertEquals(obj1.getId(), afterGet.getId());
        assertNull(afterGet.getHidden());
    }

    @Test
    public void testCreate_ignoredFieldShouldNotBePopulated() throws Exception {
        //given
        JacksonExperiment obj = DataGenerator.generateJacksonExperiment();
        //when
        Long id = template.postForEntity(base.toString(), obj, CreatedResponse.class).getBody().getId();
        //then
        assertNotNull(id);
        JacksonExperiment saved = jacksonExperimentRepository.findOne(id);
        assertEquals(saved.getCreatedOn(), obj.getCreatedOn());
        assertNull(saved.getHidden());
    }

    @Test
    public void testCreate_whenObjectHasBeenDeserializedAndSerializedAgain() throws Exception {
        //given
        JacksonExperiment obj = DataGenerator.generateJacksonExperiment();
        String json = objectMapper.writeValueAsString(obj);
        //when
        Long id = template.postForEntity(base.toString(), objectMapper.readValue(json, JacksonExperiment.class), CreatedResponse.class).getBody().getId();
        //then
        assertNotNull(id);
        JacksonExperiment saved = jacksonExperimentRepository.findOne(id);
        assertEquals(saved.getCreatedOn(), obj.getCreatedOn());
        assertNull(saved.getHidden());
    }
}