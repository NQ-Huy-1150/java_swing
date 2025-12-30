package com.java_swing_project.main.java.service;

import com.java_swing_project.main.java.repository.JdbcRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    private final JdbcRepository jdbcRepository;

    public ConnectionTest() {
        jdbcRepository = new JdbcRepository();

        String sql = "SELECT * FROM pet_hotel.customers;";
        try {
            Connection connection = this.jdbcRepository.dbConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                long id = rs.getLong("id");
                System.out.println("id : "  + id);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        new ConnectionTest();
    }
}
