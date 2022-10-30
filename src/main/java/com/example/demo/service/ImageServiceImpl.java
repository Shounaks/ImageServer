package com.example.demo.service;

import com.example.demo.config.StorageProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import static com.example.demo.service.FileCommons.*;

@Slf4j
@Service
public class ImageServiceImpl implements ImageService{

    @Autowired
    ServletContext servletContext;
    public static final String IMAGE_KEY = "image";
    private final String imageStoragePath;
    private final List<String> allowedProperties;

    public ImageServiceImpl(StorageProperties imageConfig) {
        allowedProperties = imageConfig.getContent().get(IMAGE_KEY).getTypes();
        imageStoragePath = imageConfig.getContent().get(IMAGE_KEY).getLocation();
        createFolderIfNotExist(imageStoragePath);
    }

    @Override
    public String uploadImage(MultipartFile image) throws IOException {
        String fileExtension = getExtensionByStringHandling(image.getOriginalFilename());
        if (fileExtension.isEmpty() || fileExtension.isBlank() || !allowedProperties.contains(fileExtension)){
            throw new UnsupportedOperationException("Extension is not supported");
        }
        String nameForStorage = generateUniqueNameForImageStorage();
        String storagePath = appendFilePathAndExtension(imageStoragePath, nameForStorage, fileExtension);
        Files.copy(image.getInputStream(), Paths.get(storagePath));
        return nameForStorage + "." + fileExtension;
    }

    @Override
    public InputStream downloadImage(String fileName) throws FileNotFoundException {
        String storagePath = imageStoragePath + fileName;
        if (new File(storagePath).exists()) return new FileInputStream(storagePath);
        else throw new FileNotFoundException("No File Of given Name Found.");
    }

    @Override
    public void deleteImage(String filename) {
        // do something
    }

    private String generateUniqueNameForImageStorage(){
        UUID uuid = UUID.randomUUID();
        if (new File(imageStoragePath + uuid).exists()){return generateUniqueNameForImageStorage();}
        else return uuid.toString();
    }

}
