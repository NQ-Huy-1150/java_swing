package com.java_swing_project.main.java.service;

import com.java_swing_project.main.java.domain.Room;
import com.java_swing_project.main.java.repository.MssSQLConnection;

import java.sql.*;

public class RoomService {
    private final MssSQLConnection mssSQLConnection;
    public RoomService() {
        mssSQLConnection = new MssSQLConnection();
    }

    public Room findRoomByName(String name) {
        Room room = new Room();
        try(Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM rooms WHERE \"name\" = \'" + name + "\'";
            System.out.println(sql);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                room.setId(rs.getLong("id"));
                room.setName(rs.getString("name"));
                String temp = rs.getString("status");
                System.out.println(">>>>>>>"+ temp);
                if (temp == null) {
                    room.setStatus("TRONG");
                }
                else room.setStatus(temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }
    public Room getRoomById(long id) {
        Room room = new Room();
        try(Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM rooms WHERE id = " + id;
            System.out.println(sql);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                room.setId(rs.getLong("id"));
                room.setName(rs.getString("name"));
                String temp = rs.getString("status");
                System.out.println(">>>>>>>"+ temp);
                if (temp == null) {
                    room.setStatus("TRONG");
                }
                else room.setStatus(temp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return room;
    }

    public void UpdateRoomById(long id, String newStatus) {
        try (Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "UPDATE rooms SET status = ? where id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, newStatus);
            pstm.setLong(2, id);

            int rows = pstm.executeUpdate();
            System.out.println(rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
