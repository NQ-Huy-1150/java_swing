package com.java_swing_project.main.java.service;


import com.java_swing_project.main.java.domain.Pet;
import com.java_swing_project.main.java.repository.MssSQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class PetService {
    private final MssSQLConnection mssSQLConnection;

    public PetService() {
        mssSQLConnection = new MssSQLConnection();
    }

    public Pet findPetById(long id) {
        Pet pet = new Pet();
        try(Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM pets WHERE \"id\" = " + id;
            System.out.println(sql);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                pet.setId(rs.getLong("id"));
                pet.setName(rs.getString("name"));
                pet.setBreed(rs.getString("breed"));
                pet.setGender(rs.getString("gender"));
                pet.setCustomerId(rs.getLong("customer_id"));
                pet.setHealthStatus(rs.getString("healthStatus"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pet;
    }

}
