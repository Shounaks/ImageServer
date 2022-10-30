package com.example.demo.controller;

import com.example.demo.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.*;

@Slf4j
@RestController
@Validated
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @PostMapping("/hello")
    public ResponseEntity<String> sayHello() {
        return new ResponseEntity<>("ImageServer is up and running...", HttpStatus.OK);
    }

    @PostMapping("/execute")
    public ResponseEntity<String> uploadImage(
            @RequestParam("image")
            @Pattern(regexp = "([A-Za-z0-9\\_\\-\\.]){1,50}", message = "Name should contain AlphaNumeric and The following Special Chars: [-,_,.] ")
            @Size(min = 1,max = 50,message = "Image Name Is Not Valid")
            MultipartFile image) throws IOException {
        String storedFileName = imageService.uploadImage(image);
        return new ResponseEntity<>(storedFileName, HttpStatus.OK);
    }

    @GetMapping("/execute")
    public ResponseEntity<InputStreamResource> downloadImage(
            @RequestParam
            @Pattern(regexp = "^(([\\w\\d]+)(\\-)?)+((\\.)?(\\w)){1,5}$",message = "Image Name Is Not Valid")
            @Size(min = 40,max = 50,message = "Image Name Is Not Valid")
            String imageName)
            throws IOException {
        InputStream resource = imageService.downloadImage(imageName);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(new InputStreamResource(resource));
    }
}
