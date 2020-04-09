package com.ds.spring.boot.hazelcast.rest;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.util.MimeType;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/hazelcast")
public class HazelcastController1 {

    private HazelcastInstance hazelcastInstance;
    private String localMap;
    private String replicatedMap;

    public HazelcastController1(HazelcastInstance hazelcastInstance, ClientConfig config) {
        this.hazelcastInstance = hazelcastInstance;
        String principal = config.getCredentials().getPrincipal();
        this.localMap = principal + ".test-cache";
        this.replicatedMap = "replicated." + principal + ".test-cache";
    }

    @RequestMapping(value="/local/{key}/{value}", method= RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Object storeLocalValue(@PathVariable String key, @PathVariable String value) {
        return hazelcastInstance.getMap(localMap).put(key,value);
    }

    @RequestMapping(value="/replicated/{key}/{value}", method= RequestMethod.PUT, produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Object storeReplicatedValue(@PathVariable String key, @PathVariable String value) {
        return hazelcastInstance.getMap(replicatedMap).put(key,value);
    }

    @RequestMapping(value="/local/{key}", method= RequestMethod.GET,produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Object getLocalValue(@PathVariable String key) {
        return hazelcastInstance.getMap(localMap).get(key);
    }

    @RequestMapping(value="/replicated/{key}", method= RequestMethod.GET,produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
    public Object getReplicatedValue(@PathVariable String key) {
        return hazelcastInstance.getMap(replicatedMap).get(key);
    }

}