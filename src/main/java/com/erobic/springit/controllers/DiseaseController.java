package com.erobic.springit.controllers;

import com.erobic.springit.models.Disease;
import com.erobic.springit.services.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by robik on 12/12/16.
 */
@RestController
@RequestMapping("/diseases")
public class DiseaseController extends AbstractController {

    @Autowired
    DiseaseService diseaseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Disease> get(Long id) {
        return ResponseEntity.ok(diseaseService.findById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody Disease disease) {
        disease = diseaseService.save(disease);
        return created(disease.getId());
    }
}