package com.erobic.springit.web.controllers;

import com.erobic.springit.entities.TransactionDemoEntity;
import com.erobic.springit.services.TransactionDemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by robik on 12/12/16.
 */
@RestController
@RequestMapping("/transaction_demo")
public class TransactionDemoController extends AbstractController {

    @Autowired
    TransactionDemoService transactionDemoService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<TransactionDemoEntity> get(Long id) {
        return ResponseEntity.ok(transactionDemoService.findById(id));
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody TransactionDemoEntity transactionDemoEntity) {
        transactionDemoEntity = transactionDemoService.save(transactionDemoEntity);
        return created(transactionDemoEntity.getId());
    }
}