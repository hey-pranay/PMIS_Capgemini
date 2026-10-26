package com.spring;


import java.sql.Connection;
import java.sql.SQLException;

public class App {

    public static void main(String[] args) {

        try (
                Connection connection = DBConnection.getConnection()
        ) {
            if (connection != null && connection.isValid(2)) {
                System.out.println("MySQL connected successfully");
                System.out.println("Database : " + connection.getCatalog());
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            System.out.println("Reason " + e.getMessage());
        }
    }
}