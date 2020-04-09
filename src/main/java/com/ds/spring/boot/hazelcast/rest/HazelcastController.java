package com.ds.spring.boot.hazelcast.rest;

import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HazelcastController {

    private HazelcastInstance hazelcastInstance;

    @RequestMapping("/write")
    public String write(@RequestParam("values")String values) {
        java.util.Map<String,String> stringStringMap = hazelcastInstance.getMap("configuration");    // get map from hazel cast
        stringStringMap.put("data",values);                 // write value, This value will be accessible from another jvm also
        return "Value has been write to Hazelcast";
    }
    @RequestMapping("/read")
    public String read() {
        java.util.Map<String,String> stringStringMap = hazelcastInstance.getMap("configuration");    // get map from hazel cast
        return "Hazelcast values is :" +stringStringMap.get("data");                    // read value
    }
}
