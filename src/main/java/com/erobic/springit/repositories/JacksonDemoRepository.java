package com.erobic.springit.repositories;

import com.erobic.springit.entities.JacksonDemoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by robik on 12/16/16.
 */
@Repository
public interface JacksonDemoRepository extends JpaRepository<JacksonDemoEntity, Long> {
}
