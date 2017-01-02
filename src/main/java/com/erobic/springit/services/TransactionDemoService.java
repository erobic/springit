package com.erobic.springit.services;

import com.erobic.springit.entities.TransactionDemoEntity;
import com.erobic.springit.repositories.TransactionDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by robik on 12/12/16.
 */
@Service
public class TransactionDemoService {
    private TransactionDemoRepository transactionDemoRepository;

    @Autowired
    public TransactionDemoService(TransactionDemoRepository transactionDemoRepository) {
        this.transactionDemoRepository = transactionDemoRepository;
    }

    public TransactionDemoEntity findById(Long id) {
        TransactionDemoEntity transactionDemoEntity = transactionDemoRepository.findOne(id);
        if (transactionDemoEntity != null) {
            transactionDemoEntity.getTransactionDemoEntityChildren();
        }
        return transactionDemoEntity;
    }

    public TransactionDemoEntity save(TransactionDemoEntity transactionDemoEntity) {
        return transactionDemoRepository.save(transactionDemoEntity);
    }

    public TransactionDemoEntity nonTransactionalSave(TransactionDemoEntity transactionDemoEntity, boolean failTransaction) {
        TransactionDemoEntity transactionDemoEntity1 = transactionDemoRepository.saveAndFlush(transactionDemoEntity);
        if (failTransaction) {
            throw new RuntimeException("Emulating a failed transaction!!");
        }
        return transactionDemoEntity1;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public TransactionDemoEntity transactionalSave(TransactionDemoEntity transactionDemoEntity, boolean failTransaction) {
        TransactionDemoEntity transactionDemoEntity1 = transactionDemoRepository.saveAndFlush(transactionDemoEntity);
        if (failTransaction) {
            throw new RuntimeException("Emulating a failed transaction!!");
        }
        return transactionDemoEntity1;
    }
}
