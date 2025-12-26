package com.example.awsapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.DeleteMessageRequest;
import software.amazon.awssdk.services.sqs.model.Message;
import software.amazon.awssdk.services.sqs.model.ReceiveMessageRequest;

@Service
public class SqsListenerService {

    private final SqsClient sqsClient;

    @Value("${aws.sqs.queue-url}")
    private String queueUrl;

    public SqsListenerService() {
        this.sqsClient = SqsClient.builder()
                .region(Region.EU_NORTH_1)
                .build();
    }

    @Scheduled(fixedRate = 5000)  // poll every 5 seconds
    public void pollMessages() {

        ReceiveMessageRequest receiveMessageRequest = ReceiveMessageRequest.builder()
                .queueUrl(queueUrl)
                .maxNumberOfMessages(5)
                .waitTimeSeconds(20)
                .build();

        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).messages();

        for (Message msg : messages) {
            System.out.println("Received from SQS: " + msg.body());

            // delete after processing
            sqsClient.deleteMessage(DeleteMessageRequest.builder()
                    .queueUrl(queueUrl)
                    .receiptHandle(msg.receiptHandle())
                    .build());
        }
    }
}
