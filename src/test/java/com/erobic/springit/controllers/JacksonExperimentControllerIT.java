package com.erobic.springit.controllers;

import com.erobic.springit.remote_models.CreatedResponse;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.JacksonExperiment;
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

import static org.assertj.core.api.Assertions.assertThat;

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
    public void testGet_shouldGetNonIgnoredFields() throws Exception {
        //given
        JacksonExperiment obj1 = DataGenerator.generateJacksonExperiment();
        obj1 = jacksonExperimentRepository.saveAndFlush(obj1);
        //when
        ResponseEntity<JacksonExperiment> response = template.getForEntity(base.toString() + "/" + obj1.getId(), JacksonExperiment.class);
        JacksonExperiment afterGet = response.getBody();
        //then
        assertThat(obj1.getId()).isEqualTo(afterGet.getId());
        assertThat(afterGet.getHidden()).isNull();
    }

    @Test
    public void testCreate_shouldPopulateNonIgnoredFields() throws Exception {
        //given
        JacksonExperiment obj = DataGenerator.generateJacksonExperiment();
        //when
        Long id = template.postForEntity(base.toString(), obj, CreatedResponse.class).getBody().getId();
        //then
        assertThat(id).isNotNull();
        JacksonExperiment saved = jacksonExperimentRepository.findOne(id);
        assertThat(saved.getCreatedOn()).isEqualTo(obj.getCreatedOn());
        assertThat(saved.getHidden()).isNullOrEmpty();
    }
}