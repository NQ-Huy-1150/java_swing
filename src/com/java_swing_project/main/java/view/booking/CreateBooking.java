/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.java_swing_project.main.java.view.booking;

import com.java_swing_project.main.java.repository.MssSQLConnection;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.sql.*;

/**
 *
 * @author huy
 */
public class CreateBooking extends javax.swing.JFrame {
    private final MssSQLConnection mssSQLConnection;
    private Object[] service; // [id, name, description, price]
    private final Object[] customer; // [id, name, phoneNumber]
    private final Object[] pet; // [id, name, breed, gender, customer_id, healthStatus]

    /**
     * Creates new form CreateBooking
     */
    public CreateBooking(long id) {
        mssSQLConnection = new MssSQLConnection();

        pet = findPetById(id);
        customer = findCustomerById((long) pet[4]); // customer_id

        setSize(500,500);
        setVisible(true);
        setTitle("Booking");
        initComponents();
        serviceDetailArea.setLineWrap(true);

        // render len view
        customerNameField.setText((String) customer[1]); // name
        petNameField.setText((String) pet[1]); // name

        serviceComboBox.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                String item = (String) serviceComboBox.getSelectedItem();
                service = getServiceByName(item);
                System.out.println(service);
                serviceDetailArea.setText(service[2] + "\n" + "Price : " + service[3]);
            }
        });

        submitBtn.addActionListener(e -> {
            Object[] room = findRoomByName((String) roomComboBox.getSelectedItem());
            System.out.println(room);

            // neu phong trong
            if (room != null && "TRONG".equals(room[2])) {

                long petId = (long) pet[0];
                long serviceId = (long) service[0];
                long roomId = (long) room[0];
                String note = noteArea.getText();

                // tao booking
                createNewBooking(petId, serviceId, roomId, note);

                // cap nhat status cua phong
                updateRoomStatus(roomId, "DANG_SU_DUNG");
                dispose();

            } else {
                JOptionPane.showMessageDialog(roomComboBox, "Phòng hiện tại không khả dụng. Vui lòng chọn phòng khác!");
            }
        });
    }

    // SQL Methods - xử lý trực tiếp database

    private Object[] findPetById(long id) {
        Object[] pet = null;
        try (Connection conn = mssSQLConnection.dbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM pets WHERE id = " + id)) {

            if (rs.next()) {
                pet = new Object[6]; // [id, name, breed, gender, customer_id, healthStatus]
                pet[0] = rs.getLong("id");
                pet[1] = rs.getString("name");
                pet[2] = rs.getString("breed");
                pet[3] = rs.getString("gender");
                pet[4] = rs.getLong("customer_id");
                pet[5] = rs.getString("healthStatus");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    private Object[] findCustomerById(long id) {
        Object[] customer = null;
        try (Connection conn = mssSQLConnection.dbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM customers WHERE id = " + id)) {

            if (rs.next()) {
                customer = new Object[3]; // [id, name, phoneNumber]
                customer[0] = rs.getLong("ID");
                customer[1] = rs.getString("NAME");
                customer[2] = rs.getString("phoneNumber");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    private Object[] getServiceByName(String name) {
        Object[] service = null;
        try (Connection conn = mssSQLConnection.dbConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM services WHERE name = ?")) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                service = new Object[4]; // [id, name, description, price]
                service[0] = rs.getLong("id");
                service[1] = rs.getString("name");
                service[2] = rs.getString("description");
                service[3] = rs.getDouble("price");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return service;
    }

    private Object[] findRoomByName(String name) {
        Object[] room = null;
        try (Connection conn = mssSQLConnection.dbConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM rooms WHERE name = '" + name + "'")) {

            if (rs.next()) {
                room = new Object[3]; // [id, name, status]
                room[0] = rs.getLong("id");
                room[1] = rs.getString("name");
                String status = rs.getString("status");
                room[2] = status == null ? "TRONG" : status;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }

    private void createNewBooking(long petId, long serviceId, long roomId, String note) {
        try (Connection conn = mssSQLConnection.dbConnection();
             PreparedStatement ps = conn.prepareStatement(
                     "INSERT INTO bookings (pet_id, service_id, room_id, note, createTime, endTime) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setLong(1, petId);
            ps.setLong(2, serviceId);
            ps.setLong(3, roomId);
            ps.setString(4, note);
            ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(6, null);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateRoomStatus(long roomId, String status) {
        try (Connection conn = mssSQLConnection.dbConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE rooms SET status = ? WHERE id = ?")) {
            ps.setString(1, status);
            ps.setLong(2, roomId);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        customerNameField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        serviceComboBox = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        petNameField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        submitBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        noteArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        serviceDetailArea = new javax.swing.JTextArea();
        jLabel6 = new javax.swing.JLabel();
        roomComboBox = new javax.swing.JComboBox<>();


        customerNameField.setEnabled(false);

        jLabel1.setText("Ghi chú :");

        serviceComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None", "COMBO_SPA_9", "TAM_SAY_CO_BAN", "CAT_TIA_TAO_KIEU", " " }));

        jLabel2.setText("Tên Khách hàng :");

        jLabel3.setText("Tên Thú cưng :");

        petNameField.setEnabled(false);

        jLabel4.setText("Mô tả dịch vụ : ");

        jLabel5.setText("Tên dịch vụ :");

        submitBtn.setText("Submit");

        noteArea.setColumns(20);
        noteArea.setLineWrap(true);
        noteArea.setRows(5);
        jScrollPane1.setViewportView(noteArea);

        serviceDetailArea.setEditable(false);
        serviceDetailArea.setColumns(20);
        serviceDetailArea.setLineWrap(true);
        serviceDetailArea.setRows(5);
        jScrollPane2.setViewportView(serviceDetailArea);

        jLabel6.setText("Chọn phòng :");

        roomComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "None",
                "phong 1",
                "phong 2",
                "phong 3",
                "phong 4",
                "phong 5",
                "phong 6",
                "phong 7",
                "phong 8",
                "phong 9",
                "phong 10",
                "phong 11",
                "phong 12" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(customerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(serviceComboBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(petNameField, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 399, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)))
                .addContainerGap(44, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(submitBtn)
                .addGap(243, 243, 243))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(petNameField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(serviceComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(roomComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(submitBtn)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(82, 82, 82))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
            // Thiết lập giao diện giống hệ điều hành đang chạy
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }


        /* Create and display the form */

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField customerNameField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea noteArea;
    private javax.swing.JTextField petNameField;
    private javax.swing.JComboBox<String> roomComboBox;
    private javax.swing.JComboBox<String> serviceComboBox;
    private javax.swing.JTextArea serviceDetailArea;
    private javax.swing.JButton submitBtn;
    // End of variables declaration//GEN-END:variables
}
