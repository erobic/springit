package com.erobic.springit.repositories;

import com.erobic.springit.models.Disease;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by robik on 12/12/16.
 */
@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
    Disease findByName(String name);
}
