package com.example.demo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ConfigurationProperties(prefix = "storage")
public class StorageProperties {
    Map<String, MultimediaContent> content = new HashMap<>();

    @Getter
    @Setter
    public static class MultimediaContent {
        List<String> types;
        String location;
    }
}

