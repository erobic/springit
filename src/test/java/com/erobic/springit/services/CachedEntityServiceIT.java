package com.erobic.springit.services;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.entities.CachedEntity;
import com.erobic.springit.repositories.CachedEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CachedEntityServiceIT extends AbstractSpringTest {
    @Autowired
    CachedEntityService cachedEntityService;
    @Autowired
    CachedEntityRepository cachedEntityRepository;

    @Test
    public void findOneReturnsCachedEntity() throws Exception {
        CachedEntity entity = new CachedEntity("findOneReturnsCachedEntity");
        entity = cachedEntityService.create(entity);
        LocalDateTime start = LocalDateTime.now();
        cachedEntityService.findOne(entity.getId());
        LocalDateTime end = LocalDateTime.now();
        long diff = Duration.between(start, end).toMillis();
        assertThat(diff).isLessThan(CachedEntityService.DELAY);
    }

    @Test
    public void findOneByPassingCacheIsDelayed() throws Exception {
        CachedEntity entity = new CachedEntity("findOneByPassingCacheIsDelayed");
        entity = cachedEntityService.create(entity);
        LocalDateTime start = LocalDateTime.now();
        cachedEntityService.findOneBypassingCache(entity.getId());
        LocalDateTime end = LocalDateTime.now();
        long diff = Duration.between(start, end).toMillis();
        assertThat(diff).isGreaterThanOrEqualTo(CachedEntityService.DELAY);
    }

    @Test
    public void deleteRemovesFromBothCacheAndDb() throws Exception {
        CachedEntity entity = new CachedEntity("deleteFromCacheAndDb");
        entity = cachedEntityService.create(entity);
        cachedEntityService.delete(entity.getId());
        assertThat(cachedEntityService.findOne(entity.getId())).isNull();
        assertThat(cachedEntityRepository.findOne(entity.getId())).isNull();
    }
}