package com.erobic.springit.services;

import com.erobic.springit.models.Disease;
import com.erobic.springit.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by robik on 12/12/16.
 */
@Service
public class TransactionDemoService {
    private DiseaseRepository diseaseRepository;

    @Autowired
    public TransactionDemoService(DiseaseRepository diseaseRepository) {
        this.diseaseRepository = diseaseRepository;
    }

    public Disease findById(Long id) {
        Disease disease = diseaseRepository.findOne(id);
        if (disease != null) {
            disease.getSymptoms();
        }
        return disease;
    }

    public Disease save(Disease disease) {
        return diseaseRepository.save(disease);
    }

    public Disease nonTransactionalSave(Disease disease, boolean failTransaction) {
        Disease disease1 = diseaseRepository.saveAndFlush(disease);
        if (failTransaction) {
            throw new RuntimeException("Emulating a failed transaction!!");
        }
        return disease1;
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Disease transactionalSave(Disease disease, boolean failTransaction) {
        Disease disease1 = diseaseRepository.saveAndFlush(disease);
        if (failTransaction) {
            throw new RuntimeException("Emulating a failed transaction!!");
        }
        return disease1;
    }
}
