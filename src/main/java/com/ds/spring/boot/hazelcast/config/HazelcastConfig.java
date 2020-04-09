package com.ds.spring.boot.hazelcast.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.config.GroupConfig;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.security.UsernamePasswordCredentials;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Slf4j
@Configuration
public class HazelcastConfig {

    @Value("${vcap.services.ds-cache.credentials.principal:principal}")
    private String hazelcastPrincipal;
    @Value("${vcap.services.ds-cache.credentials.password:password}")
    private String hazelcastPassword;
    @Value("${vcap.services.ds-cache.credentials.group:group}")
    private String hazelcastGroup;
    @Value("${vcap.services.ds-cache.credentials.ips:127.0.0.1}")
    private List<String> hazelcastNodeIps;
    @Value("${hazelcast.use-embedded:false}")
    private boolean useEmbeddedHazelcast;

    @Bean
    public ClientConfig clientConfig() {
        ClientConfig config = new ClientConfig();
        config.setExecutorPoolSize(10000)
                .setGroupConfig(new GroupConfig(hazelcastGroup))
                .setCredentials(new UsernamePasswordCredentials(hazelcastPrincipal, hazelcastPassword))
                .getNetworkConfig()
                .setAddresses(hazelcastNodeIps)
                .setSmartRouting(true);
        return config;
    }

    @Bean
    public HazelcastInstance hazelcastInstance(ClientConfig config) {
        if (useEmbeddedHazelcast) {
            return Hazelcast.newHazelcastInstance();
        }

        HazelcastInstance hazelcastInstance = null;
        try {
            hazelcastInstance = HazelcastClient.newHazelcastClient(config);
        } catch (Exception e) {
            log.warn("Not able to connect to cluster {}.", hazelcastGroup);
        }

        return hazelcastInstance;
    }
}
