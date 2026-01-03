package com.java_swing_project.main.java.view.booking.homepage;

import com.java_swing_project.main.java.repository.MssSQLConnection;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InvoicePanelView {
    private final MssSQLConnection mssSQLConnection;
    private JTable tableinvoice;
    private DefaultTableModel modelinvoice;

    public InvoicePanelView(MssSQLConnection mssSQLConnection, JScrollPane scronpanelinvoice) throws SQLException {
        this.mssSQLConnection = mssSQLConnection;
        thietlaptable(scronpanelinvoice);
    }

    public void thietlaptable(JScrollPane scrollpane) throws SQLException {
        modelinvoice = new DefaultTableModel();
        modelinvoice.addColumn("ID");
        modelinvoice.addColumn("booking_id");
        modelinvoice.addColumn("Tên Pet");
        modelinvoice.addColumn("Tên dịch vụ");
        modelinvoice.addColumn("Tổng tiền");

        tableinvoice = new JTable(modelinvoice);
        scrollpane.setViewportView(tableinvoice);
        loaddata();
    }

    private String baseInvoiceQuery() {
        // Join thêm bảng services thông qua service_id của bảng bookings
        return """
                SELECT
                    i.id AS invoice_id,
                    i.booking_id,
                    p.name AS pet_name,
                    s.name AS service_name,
                    i.total
                FROM invoices i
                JOIN bookings b ON i.booking_id = b.id
                JOIN pets p ON b.pet_id = p.id
                JOIN services s ON b.service_id = s.id
                """;
    }

    private void executeAndFillTable(String sql, Object... params) throws SQLException {
        try (Connection conn = mssSQLConnection.dbConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            for (int i = 0; i < params.length; i++) {
                ps.setObject(i + 1, params[i]);
            }

            try (ResultSet rs = ps.executeQuery()) {
                modelinvoice.setRowCount(0);
                while (rs.next()) {
                    Object[] row = {
                            rs.getLong("invoice_id"),
                            rs.getLong("booking_id"),
                            rs.getString("pet_name"),
                            rs.getString("service_name"),
                            rs.getDouble("total")
                    };
                    modelinvoice.addRow(row);
                }
            }
        }
    }

    public void loaddata() throws SQLException {
        executeAndFillTable(baseInvoiceQuery());
    }


    public void searchInvoiceByPetName() throws SQLException {
        String petName = JOptionPane.showInputDialog(null,"Nhập vào tên pet : ");

        if (petName == null || petName.trim().isEmpty()) {
            loaddata();
            return;
        }

        String sql = baseInvoiceQuery() + "\nWHERE p.name LIKE ?\nORDER BY i.id DESC";
        executeAndFillTable(sql, "%" + petName.trim() + "%");
    }

    private void reloadData() throws SQLException {
        loaddata();
    }

}
