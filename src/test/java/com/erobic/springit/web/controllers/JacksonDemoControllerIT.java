package com.erobic.springit.web.controllers;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.remote_models.CreatedResponse;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.JacksonDemoEntity;
import com.erobic.springit.repositories.JacksonDemoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JacksonDemoControllerIT extends AbstractSpringTest{
    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;
    @Autowired
    JacksonDemoRepository jacksonDemoRepository;
    @Autowired
    ObjectMapper objectMapper;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/jackson");
    }

    @Test
    public void testGet_shouldGetNonIgnoredFields() throws Exception {
        //given
        JacksonDemoEntity obj1 = DataGenerator.generateJacksonExperiment();
        obj1 = jacksonDemoRepository.saveAndFlush(obj1);
        //when
        ResponseEntity<JacksonDemoEntity> response = template.getForEntity(base.toString() + "/" + obj1.getId(), JacksonDemoEntity.class);
        JacksonDemoEntity afterGet = response.getBody();
        //then
        assertThat(obj1.getId()).isEqualTo(afterGet.getId());
        assertThat(afterGet.getHidden()).isNull();
    }

    @Test
    public void testCreate_shouldPopulateNonIgnoredFields() throws Exception {
        //given
        JacksonDemoEntity obj = DataGenerator.generateJacksonExperiment();
        //when
        Long id = template.postForEntity(base.toString(), obj, CreatedResponse.class).getBody().getId();
        //then
        assertThat(id).isNotNull();
        JacksonDemoEntity saved = jacksonDemoRepository.findOne(id);
        assertThat(saved.getCreatedOn()).isEqualTo(obj.getCreatedOn());
        assertThat(saved.getHidden()).isNullOrEmpty();
    }
}