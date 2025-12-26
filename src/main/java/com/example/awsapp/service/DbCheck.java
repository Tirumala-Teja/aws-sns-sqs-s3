package com.example.awsapp.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DbCheck {

    @Autowired
    private DataSource dataSource;

    @PostConstruct
    public void test() throws Exception {
        System.out.println("Connected to RDS: " + dataSource.getConnection());
    }
}

