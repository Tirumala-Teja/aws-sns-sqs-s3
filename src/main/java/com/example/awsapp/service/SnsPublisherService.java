package com.example.awsapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;

@Service
public class SnsPublisherService {

    private final SnsClient snsClient;

    @Value("${aws.sns.topic-arn}")
    private String snsTopicArn;

    public SnsPublisherService() {
        this.snsClient = SnsClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    public void publishUploadEvent(String fileName, String s3Url) {

        String message = String.format(
            "File uploaded: %s, URL: %s", fileName, s3Url
        );

        PublishRequest request = PublishRequest.builder()
                .topicArn(snsTopicArn)
                .message(message)
                .build();

        snsClient.publish(request);
    }
}
