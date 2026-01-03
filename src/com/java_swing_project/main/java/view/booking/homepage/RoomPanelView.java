package com.java_swing_project.main.java.view.booking.homepage;

import com.java_swing_project.main.java.repository.MssSQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomPanelView {
    private final MssSQLConnection mssSQLConnection;
    private final JTable roomTable;
    private final DefaultTableModel roomModel;
    private final String[] COLUMN_NAMES = {"Id", "Tên phòng", "Trạng thái phòng"};

    public RoomPanelView(MssSQLConnection mssSQLConnection, JTable roomTable) {
        this.mssSQLConnection = mssSQLConnection;
        this.roomTable = roomTable;
        this.roomModel = new DefaultTableModel(COLUMN_NAMES, 0);

        initializeRoomPanel();
    }

    private void initializeRoomPanel() {
        loadRoomData();
        roomTable.setModel(roomModel);
    }

    public void loadRoomData() {
        roomModel.setRowCount(0);
        List<Object[]> rooms = getAllRoom();

        for (Object[] room : rooms) {
            roomModel.addRow(new Object[]{room[0], room[1], room[2]});
        }
    }


    public void reloadRoomTable() {
        loadRoomData();
    }

    // SQL Methods - xử lý trực tiếp database

    private List<Object[]> getAllRoom() {
        List<Object[]> rooms = new ArrayList<>();
        try (Connection conn = mssSQLConnection.dbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM rooms")) {

            while (rs.next()) {
                Object[] room = new Object[3]; // [id, name, status]
                room[0] = rs.getLong("id");
                room[1] = rs.getString("name");
                room[2] = rs.getString("status");
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public JTable getRoomTable() {
        return roomTable;
    }

    public DefaultTableModel getRoomModel() {
        return roomModel;
    }


}

