package com.ds.spring.boot.hazelcast;

import com.hazelcast.core.HazelcastInstance;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.core.SchedulerLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CacheManagerService {

    private EmployeeService employeeService;

    public static final String HAZELCAST_PRINCIPAL_NAME = "test-org.test-space.test-cache."; // organization-space-cache

    public static final String CACHE_COMPONENTS = HAZELCAST_PRINCIPAL_NAME + "sample-cache-object";
    public static final String PAGE_CACHE_COMPONENTS = HAZELCAST_PRINCIPAL_NAME + "page-cache";

    @Scheduled(cron = "0 0 3 * * *")
    @SchedulerLock(name = HAZELCAST_PRINCIPAL_NAME + "cachefilllock",
            lockAtLeastForString = "PT2H", lockAtMostForString = "PT3H")

    @CacheEvict(cacheNames = {CACHE_COMPONENTS}, beforeInvocation = true, allEntries = true)
    public void refreshCaches() {
        log.info("Started with building the categories cache by invoking the query method on the category service");
        employeeService.getAllEmployee();
        log.info("Finished with building the categories");
    }

    @CacheEvict(cacheNames = {CACHE_COMPONENTS}, beforeInvocation = true, allEntries = true)
    public void clearCaches() {
        log.info("Flushed all caches");
    }

    @CacheEvict(value = PAGE_CACHE_COMPONENTS, key = "#pageId")
    public void removeFromPageCache(String pageId) {
        log.debug("Removed page {} from cache {}", pageId, PAGE_CACHE_COMPONENTS);
    }

}
