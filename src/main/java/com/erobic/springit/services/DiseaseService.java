package com.erobic.springit.services;

import com.erobic.springit.models.Disease;
import com.erobic.springit.repositories.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by robik on 12/12/16.
 */
@Service
public class DiseaseService {
    @Autowired
    DiseaseRepository diseaseRepository;

    public Disease findById(Long id) {
        Disease disease = diseaseRepository.findOne(id);
        if (disease != null) {
            disease.getSymptoms();
        }
        return disease;
    }

    public Disease save(Disease disease){
        return diseaseRepository.save(disease);
    }
}
