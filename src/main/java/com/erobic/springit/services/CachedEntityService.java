package com.erobic.springit.services;

import com.erobic.springit.entities.CachedEntity;
import com.erobic.springit.repositories.CachedEntityRepository;
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
@Service("cachedEntityService")
public class CachedEntityService {

    public static final Long DELAY = 2000l;
    private CachedEntityRepository cachedEntityRepository;

    @Autowired
    public CachedEntityService(CachedEntityRepository cachedEntityRepository) {
        this.cachedEntityRepository = cachedEntityRepository;
    }

    @CachePut(value = "cachedEntities", key = "#result.id")
    public CachedEntity create(CachedEntity entity) {
        return cachedEntityRepository.save(entity);
    }

    @CachePut(value = "cachedEntities", key = "#cachedEntity.id")
    public CachedEntity update(CachedEntity entity) {
        return cachedEntityRepository.save(entity);
    }

    @Cacheable(value = "cachedEntities", key = "#id")
    public CachedEntity findOne(Long id) {
        emulateDelay();
        return cachedEntityRepository.findOne(id);
    }

    public CachedEntity findOneBypassingCache(Long id){
        emulateDelay();
        return cachedEntityRepository.findOne(id);
    }

    @CacheEvict(value = "cachedEntities", key = "#id")
    public void delete(Long id) {
        cachedEntityRepository.delete(id);
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
