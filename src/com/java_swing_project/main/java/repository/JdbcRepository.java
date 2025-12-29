package com.java_swing_project.main.java.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcRepository {
    public Connection dbConnection() {
        Connection connection = null;
        try {
            System.out.println("Connecting to MySql.....");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pet_hotel",
                    "root", "123456");
            System.out.println("Connection Established successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
