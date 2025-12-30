package com.java_swing_project.main.java.service;

import com.java_swing_project.main.java.repository.MssSQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionTest {
    private final MssSQLConnection mssSQLConnection;

    public ConnectionTest() {
        mssSQLConnection = new MssSQLConnection();

        String sql = "SELECT * FROM dbo.customers;";
        try {
            Connection connection = this.mssSQLConnection.dbConnection();
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
