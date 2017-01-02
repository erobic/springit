package com.erobic.springit.services;

import com.erobic.springit.AbstractSpringTest;
import com.erobic.springit.entities.CachingDemoEntity;
import com.erobic.springit.repositories.CachingDemoRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CachingDemoServiceIT extends AbstractSpringTest {
    @Autowired
    CachingDemoService cachingDemoService;
    @Autowired
    CachingDemoRepository cachingDemoRepository;

    @Test
    public void findOneReturnsCachedEntity() throws Exception {
        CachingDemoEntity entity = new CachingDemoEntity("findOneReturnsCachedEntity");
        entity = cachingDemoService.create(entity);
        LocalDateTime start = LocalDateTime.now();
        cachingDemoService.findOne(entity.getId());
        LocalDateTime end = LocalDateTime.now();
        long diff = Duration.between(start, end).toMillis();
        assertThat(diff).isLessThan(CachingDemoService.DELAY);
    }

    @Test
    public void findOneByPassingCacheIsDelayed() throws Exception {
        CachingDemoEntity entity = new CachingDemoEntity("findOneByPassingCacheIsDelayed");
        entity = cachingDemoService.create(entity);
        LocalDateTime start = LocalDateTime.now();
        cachingDemoService.findOneBypassingCache(entity.getId());
        LocalDateTime end = LocalDateTime.now();
        long diff = Duration.between(start, end).toMillis();
        assertThat(diff).isGreaterThanOrEqualTo(CachingDemoService.DELAY);
    }

    @Test
    public void deleteRemovesFromBothCacheAndDb() throws Exception {
        CachingDemoEntity entity = new CachingDemoEntity("deleteFromCacheAndDb");
        entity = cachingDemoService.create(entity);
        cachingDemoService.delete(entity.getId());
        assertThat(cachingDemoService.findOne(entity.getId())).isNull();
        assertThat(cachingDemoRepository.findOne(entity.getId())).isNull();
    }
}