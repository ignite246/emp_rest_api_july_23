package com.rahul.projects.EmployeeRESTAPI.cacheconfig;

import com.hazelcast.config.Config;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeCacheConfig {

    @Bean
    public Config cacheConfig(){
        return new Config()
                .setInstanceName("hazel-instance")
                .addMapConfig(new MapConfig()
                        .setName("employee-cache")
                        .setTimeToLiveSeconds(3000));


    }
}
