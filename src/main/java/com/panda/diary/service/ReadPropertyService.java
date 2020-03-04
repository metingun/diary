package com.panda.diary.service;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class ReadPropertyService {

    private final Environment env;

    public ReadPropertyService(Environment env) {
        this.env = env;
    }

    public String getPropertyValue(String key) {
        return env.getProperty(key);
    }
}
