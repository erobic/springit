package com.erobic.springit.services;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.Disease;
import com.erobic.springit.repositories.DiseaseRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionDemoServiceTest extends AbstractSpringTest {
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
        assertThat(diseaseRepository.findByName(savedDisease.getName())).isNotNull();
    }

    @Test
    public void testNonTransactionalSave_shouldNotSaveWhenException() throws Exception {
        //given
        Disease disease = DataGenerator.generateDisease();
        try {
            transactionDemoService.transactionalSave(disease, true);
        } catch (Exception e) {
        }
        //then
        assertThat(diseaseRepository.findByName(disease.getName())).isNull();
    }

    @Test
    public void testNonTransactionalSave_shouldSaveWhenEvenWhenException() throws Exception {
        //given
        Disease disease = DataGenerator.generateDisease();
        try {
            transactionDemoService.nonTransactionalSave(disease, true);
        } catch (Exception e) {
        }
        //then
        assertThat(diseaseRepository.findByName(disease.getName())).isNotNull();
    }
}