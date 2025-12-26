package com.example.awsapp.controller;

import com.example.awsapp.service.S3Service;
import com.example.awsapp.service.SnsPublisherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class S3Controller {

    @Autowired
    private S3Service s3Service;
    
    @Autowired
    SnsPublisherService snsPublisherService;

    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<String> upload(@RequestPart("file")  MultipartFile f) throws Exception {
         String uploadedUrl = s3Service.upload(f);

        snsPublisherService.publishUploadEvent(f.getOriginalFilename(), uploadedUrl);

        return ResponseEntity.ok("Uploaded & SNS message sent");
    }
}
