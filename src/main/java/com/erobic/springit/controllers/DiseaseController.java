package com.erobic.springit.controllers;

import com.erobic.springit.entities.Disease;
import com.erobic.springit.services.TransactionDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by robik on 12/12/16.
 */
@RestController
@RequestMapping("/diseases")
public class DiseaseController extends AbstractController {

    @Autowired
    TransactionDemoService transactionDemoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<Disease> get(Long id) {
        return ResponseEntity.ok(transactionDemoService.findById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody Disease disease) {
        disease = transactionDemoService.save(disease);
        return created(disease.getId());
    }
}