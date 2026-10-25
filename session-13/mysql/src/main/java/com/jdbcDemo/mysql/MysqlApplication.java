package com.jdbcDemo.mysql;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class MysqlApplication implements CommandLineRunner {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    public static void main(String[] args) {
        SpringApplication.run(MysqlApplication.class, args);
        System.out.println("JDBC session");
    }

    @Override
    public void run(String... args) {
        try (Connection connection =
                     DriverManager.getConnection(url, username, password)) {

            if (connection.isValid(2)) {
                System.out.println("MySQL Connected Successfully!");
            }

        } catch (SQLException e) {
            System.out.println("MySQL Connection Failed!");
            System.out.println(e.getMessage());
        }
    }

}