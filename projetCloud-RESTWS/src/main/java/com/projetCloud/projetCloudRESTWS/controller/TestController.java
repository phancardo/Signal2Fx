package com.projetCloud.projetCloudRESTWS.controller;

import com.projetCloud.projetCloudRESTWS.model.ImageSignalement;
import com.projetCloud.projetCloudRESTWS.model.Test;
import com.projetCloud.projetCloudRESTWS.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

@RestController
@CrossOrigin(value = "*",maxAge = 3600)
public class TestController {

    @Autowired
    private FileStorageService storageService;

    @PostMapping("/test")
    public ResponseEntity<?> test(@ModelAttribute Test test,@RequestParam("hello") MultipartFile file){
        String message = "";
        try {
            storageService.save(file);
            test.setName(file.getOriginalFilename());
            message = "Uploaded the file successfully: " + file.getOriginalFilename();
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
}
