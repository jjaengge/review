package com.trip.review;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties
@ConfigurationProperties
public class Configuration {

    private Map<String, Boolean> threadService = new HashMap<>();

    public Map<String, Boolean> getThreadService() {
        return threadService;
    }

    public void setThreadService(Map<String, Boolean> threadService) {
        this.threadService = threadService;
    }

    public boolean getThreadServiceFlag(String key) {
        if (Optional.ofNullable(threadService.get(key)).isPresent()) {
            return threadService.get(key);
        } else {
            return false;
        }
    }
}

