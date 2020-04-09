package com.ds.spring.boot.hazelcast.rest;

import com.ds.spring.boot.hazelcast.CacheManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

public class CacheManagerController {

    private final CacheManagerService cacheManagerService;

    public CacheManagerController(CacheManagerService cacheManagerService) {
        this.cacheManagerService = cacheManagerService;
    }

    @GetMapping
    public ResponseEntity<String> clear() {
        cacheManagerService.clearCaches();
        return ResponseEntity.ok("Caches are cleared");
    }
}
