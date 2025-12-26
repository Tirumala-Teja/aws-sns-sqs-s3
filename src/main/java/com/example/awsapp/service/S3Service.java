package com.example.awsapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Service
public class S3Service {
	@Autowired
	 private  AwsSecretsManagerService secretsService;
    private final S3Client s3 = S3Client.builder().region(Region.AP_SOUTH_1).build();

    public String upload(MultipartFile file) throws Exception {
    	  S3Secret secret = secretsService.getS3Credentials();

          S3Client s3 = S3Client.builder()
                  .credentialsProvider(
                          StaticCredentialsProvider.create(
                                  AwsBasicCredentials.create(secret.getAccessKey(), secret.getSecretKey())
                          )
                  )
                  .region(software.amazon.awssdk.regions.Region.of(secret.getRegion()))
                  .build();

          s3.putObject(
                  PutObjectRequest.builder()
                          .bucket(secret.getBucketName()) // bucket name comes from secret
                          .key(file.getOriginalFilename())
                          .contentType(file.getContentType())
                          .build(),
                  RequestBody.fromBytes(file.getBytes())
          );

          return "Uploaded Successfully → " + file.getOriginalFilename();
    }
}
