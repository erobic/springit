package com.erobic.springit.controllers;

import com.erobic.springit.models.JacksonExperiment;
import com.erobic.springit.repositories.JacksonExperimentRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by robik on 12/16/16.
 */
@RestController
@RequestMapping("/jackson")
public class JacksonExperimentController extends AbstractController<JacksonExperiment> {
    @Autowired
    JacksonExperimentRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JacksonExperiment> get(@PathVariable("id") Long id) {
        return get(repository.findOne(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody JacksonExperiment jacksonExperiment) throws JsonProcessingException {
        System.out.println("value as wstring: "+objectMapper.writeValueAsString(jacksonExperiment));
        JacksonExperiment saved = repository.saveAndFlush(jacksonExperiment);
        return created(saved.getId());
    }

}
