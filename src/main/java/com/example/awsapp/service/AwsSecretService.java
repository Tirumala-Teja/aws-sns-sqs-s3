package com.example.awsapp.service;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Service
public class AwsSecretService {

    public String getDbPassword() {

        SecretsManagerClient client = SecretsManagerClient.builder()
                .region(Region.AP_SOUTH_1)
                .build();

        GetSecretValueResponse response = client.getSecretValue(
                GetSecretValueRequest.builder()
                        .secretId("rds/mysql/springboot")
                        .build()
        );

        JSONObject json = new JSONObject(response.secretString());
        return json.getString("password");
    }
}
