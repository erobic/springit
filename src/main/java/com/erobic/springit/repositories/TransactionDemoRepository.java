package com.erobic.springit.repositories;

import com.erobic.springit.entities.TransactionDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by robik on 12/12/16.
 */
@Repository
public interface TransactionDemoRepository extends JpaRepository<TransactionDemoEntity, Long> {
    TransactionDemoEntity findByName(String name);
}
