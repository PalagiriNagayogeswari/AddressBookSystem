package com.bridgelabz.addressbook.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;

@Configuration
@EnableWebMvc
@ComponentScan("com.bridgelabz.addressbook")
public class AppConfig {

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource =
                new DriverManagerDataSource();

        dataSource.setDriverClassName(
                "org.postgresql.Driver"
        );

        dataSource.setUrl(
                "jdbc:postgresql://localhost:5432/addressBook"
        );

        dataSource.setUsername("postgres");

        dataSource.setPassword("Yogi@180523");

        return dataSource;
    }
}