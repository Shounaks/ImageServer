package com.example.demo.service;

import lombok.experimental.UtilityClass;

import java.io.File;
import java.util.Optional;

@UtilityClass
public class FileCommons {
    static void createFolderIfNotExist(String path){
        File storedFile = new File(path);
        if (!storedFile.exists()) storedFile.mkdir();
    }
    static String getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1))
                .orElse("");
    }

    static String appendFilePathAndExtension(String imageStoragePath,String fileName,String extension){
        return imageStoragePath.concat(fileName.concat("." + extension));
    }
}
