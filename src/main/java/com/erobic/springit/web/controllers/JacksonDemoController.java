package com.erobic.springit.web.controllers;

import com.erobic.springit.entities.JacksonDemoEntity;
import com.erobic.springit.repositories.JacksonDemoRepository;
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
public class JacksonDemoController extends AbstractController<JacksonDemoEntity> {
    @Autowired
    JacksonDemoRepository repository;

    @Autowired
    ObjectMapper objectMapper;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<JacksonDemoEntity> get(@PathVariable("id") Long id) {
        return get(repository.findOne(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody JacksonDemoEntity jacksonDemoEntity) throws JsonProcessingException {
        System.out.println("value as wstring: "+objectMapper.writeValueAsString(jacksonDemoEntity));
        JacksonDemoEntity saved = repository.saveAndFlush(jacksonDemoEntity);
        return created(saved.getId());
    }

}
