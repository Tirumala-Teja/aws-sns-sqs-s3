package com.example.awsapp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import org.springframework.stereotype.Service;

@Service
public class AwsSecretsManagerService {

    public S3Secret getS3Credentials() throws Exception {

        var client = SecretsManagerClient.builder()
                .region(software.amazon.awssdk.regions.Region.AP_SOUTH_1)
                .build();

        var response = client.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId("s3/app/credentials") 
                        .build()
        );

        return new ObjectMapper().readValue(response.secretString(), S3Secret.class);
    }
}
