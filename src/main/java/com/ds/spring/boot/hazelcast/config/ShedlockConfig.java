package com.ds.spring.boot.hazelcast.config;

import com.hazelcast.core.HazelcastInstance;
import net.javacrumbs.shedlock.provider.hazelcast.HazelcastLockProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShedlockConfig {

    @Bean
    public HazelcastLockProvider lockProvider(HazelcastInstance hazelcastInstance) {
        return new HazelcastLockProvider(hazelcastInstance);
    }
}
