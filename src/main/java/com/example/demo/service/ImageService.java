package com.example.demo.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public interface ImageService {

    String uploadImage(MultipartFile file) throws IOException;
    InputStream downloadImage(String fileName) throws FileNotFoundException;
    void deleteImage(String filename);
}
