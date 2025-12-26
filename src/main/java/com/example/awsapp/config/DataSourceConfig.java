package com.example.awsapp.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.awsapp.service.AwsSecretService;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Autowired
    AwsSecretService secretService;

    @Bean
    public DataSource dataSource() {

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl("jdbc:mysql://springboot-rds.cxgu6mie0um6.ap-south-1.rds.amazonaws.com:3306/RDS_Schema");
        ds.setUsername("admin");
        ds.setPassword(secretService.getDbPassword());
        return ds;
    }
}
