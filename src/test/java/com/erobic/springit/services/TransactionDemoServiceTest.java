package com.erobic.springit.services;

import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.models.Disease;
import com.erobic.springit.repositories.DiseaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionDemoServiceTest {
    @Autowired
    TransactionDemoService transactionDemoService;
    @Autowired
    DiseaseRepository diseaseRepository;

    @Test
    public void testTransactionalSave_shouldSaveWhenNoException() throws Exception {
        //given
        Disease disease = DataGenerator.generateDisease();
        //when
        Disease savedDisease = transactionDemoService.transactionalSave(disease, false);
        //then
        assertNotNull(diseaseRepository.findByName(savedDisease.getName()));
    }

    @Test
    public void testNonTransactionalSave_shouldNotSaveWhenException() throws Exception {
        //given
        Disease disease = DataGenerator.generateDisease();
        try {
            transactionDemoService.transactionalSave(disease, true);
        } catch (Exception e) {}
        //then
        assertNull(diseaseRepository.findByName(disease.getName()));
    }

    @Test
    public void testNonTransactionalSave_shouldSaveWhenEvenWhenException() throws Exception {
        //given
        Disease disease = DataGenerator.generateDisease();
        try {
            transactionDemoService.nonTransactionalSave(disease, true);
        } catch (Exception e) {}
        //then
        assertNotNull(diseaseRepository.findByName(disease.getName()));
    }
}