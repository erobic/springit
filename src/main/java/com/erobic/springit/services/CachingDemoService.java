package com.erobic.springit.services;

import com.erobic.springit.entities.CachingDemoEntity;
import com.erobic.springit.repositories.CachingDemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Each CRUD functionality emulates delay and fetches/manipulates data item
 * However since entities are cached, when using this service, the delay should have no effect!
 * Created by robik on 12/24/16.
 */
@Service("cachingDemoService")
public class CachingDemoService {

    public static final Long DELAY = 2000l;
    private CachingDemoRepository cachingDemoRepository;

    @Autowired
    public CachingDemoService(CachingDemoRepository cachingDemoRepository) {
        this.cachingDemoRepository = cachingDemoRepository;
    }

    @CachePut(value = "cachedEntities", key = "#result.id")
    public CachingDemoEntity create(CachingDemoEntity entity) {
        return cachingDemoRepository.save(entity);
    }

    @CachePut(value = "cachedEntities", key = "#cachedEntity.id")
    public CachingDemoEntity update(CachingDemoEntity entity) {
        return cachingDemoRepository.save(entity);
    }

    @Cacheable(value = "cachedEntities", key = "#id")
    public CachingDemoEntity findOne(Long id) {
        emulateDelay();
        return cachingDemoRepository.findOne(id);
    }

    public CachingDemoEntity findOneBypassingCache(Long id){
        emulateDelay();
        return cachingDemoRepository.findOne(id);
    }

    @CacheEvict(value = "cachedEntities", key = "#id")
    public void delete(Long id) {
        cachingDemoRepository.delete(id);
    }

    @CacheEvict(value = "cachedEntities", allEntries = true)
    public void eviceAll() {
    }

    private void emulateDelay() {
        try {
            Thread.sleep(DELAY);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
