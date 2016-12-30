package com.erobic.springit.repositories;

import com.erobic.springit.entities.CachedEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by robik on 12/24/16.
 */
@Repository
public interface CachedEntityRepository extends JpaRepository<CachedEntity, Long> {
    List<CachedEntity> findByName(String name);
}
