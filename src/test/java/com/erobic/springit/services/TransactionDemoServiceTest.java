package com.erobic.springit.services;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.generator.DataGenerator;
import com.erobic.springit.entities.TransactionDemoEntity;
import com.erobic.springit.repositories.TransactionDemoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TransactionDemoServiceTest extends AbstractSpringTest {
    @Autowired
    TransactionDemoService transactionDemoService;
    @Autowired
    TransactionDemoRepository transactionDemoRepository;

    @Test
    public void testTransactionalSave_shouldSaveWhenNoException() throws Exception {
        //given
        TransactionDemoEntity transactionDemoEntity = DataGenerator.generateDisease();
        //when
        TransactionDemoEntity savedTransactionDemoEntity = transactionDemoService.transactionalSave(transactionDemoEntity, false);
        //then
        assertThat(transactionDemoRepository.findByName(savedTransactionDemoEntity.getName())).isNotNull();
    }

    @Test
    public void testNonTransactionalSave_shouldNotSaveWhenException() throws Exception {
        //given
        TransactionDemoEntity transactionDemoEntity = DataGenerator.generateDisease();
        try {
            transactionDemoService.transactionalSave(transactionDemoEntity, true);
        } catch (Exception e) {
        }
        //then
        assertThat(transactionDemoRepository.findByName(transactionDemoEntity.getName())).isNull();
    }

    @Test
    public void testNonTransactionalSave_shouldSaveWhenEvenWhenException() throws Exception {
        //given
        TransactionDemoEntity transactionDemoEntity = DataGenerator.generateDisease();
        try {
            transactionDemoService.nonTransactionalSave(transactionDemoEntity, true);
        } catch (Exception e) {
        }
        //then
        assertThat(transactionDemoRepository.findByName(transactionDemoEntity.getName())).isNotNull();
    }
}