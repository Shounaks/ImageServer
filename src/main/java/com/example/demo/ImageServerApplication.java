package com.example.demo;

import com.example.demo.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties({StorageProperties.class})
public class ImageServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ImageServerApplication.class, args);
    }
}