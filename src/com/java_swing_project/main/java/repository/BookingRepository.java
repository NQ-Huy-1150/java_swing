package com.java_swing_project.main.java.repository;

import com.java_swing_project.main.java.domain.Booking;
import com.java_swing_project.main.java.domain.Service;
import com.java_swing_project.main.java.service.BookingService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookingRepository {
    private final MssSQLConnection mssSQLConnection;
    public BookingRepository() {
        mssSQLConnection = new MssSQLConnection();
    }
    public List<Service> getAllService() {
        List<Service> services = new ArrayList<>();
        try (Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM [pet_hotel].[dbo].[services]";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Service service = new Service();
                service.setId(rs.getLong("id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setPrice(rs.getDouble("price"));
                services.add(service);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return services;
    }
    public List<Booking> getAllBooking() {
        List<Booking> bookings = new ArrayList<>();
        try (Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM bookings";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Booking booking = new Booking();
                booking.setId(rs.getLong("id"));
                booking.setPetId(rs.getLong("pet_id"));
                booking.setRoomId(rs.getLong("room_id"));
                booking.setServiceId(rs.getLong("service_id"));
                booking.setCreateTime(String.valueOf(rs.getTimestamp("createTime")));
                booking.setEndTime(String.valueOf(rs.getTime("endTime")));
                booking.setNote(rs.getString("note"));
                bookings.add(booking);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return bookings;
    }

    public void deleteBookingById(long id) {
            try (Connection connection = mssSQLConnection.dbConnection()) {
                String sql = "DELETE FROM bookings where id = ?";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setLong(1, id);
                int rows = pstm.executeUpdate();
                System.out.println(rows);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }

    public Booking findBookingById(long id) {
        Booking booking = new Booking();
        try (Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM bookings WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1,id);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                booking.setId(rs.getLong("id"));
                booking.setPetId(rs.getLong("pet_id"));
                booking.setRoomId(rs.getLong("room_id"));
                booking.setServiceId(rs.getLong("service_id"));
                booking.setCreateTime(String.valueOf(rs.getTime("createTime")));
                booking.setEndTime(String.valueOf(rs.getTime("endTime")));
                booking.setNote(rs.getString("note"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return booking;
    }
    public void createBooking (Booking booking) {
        try (Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "INSERT INTO bookings (service_id, room_id, pet_id, createTime, note) values (?,?,?,?,?)";

            //lay thoi gian hien tai
            java.time.LocalDateTime now = java.time.LocalDateTime.now();

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1, booking.getServiceId());
            pstm.setLong(2, booking.getRoomId());
            pstm.setLong(3, booking.getPetId());
            // thoi gian tao phieu
            pstm.setTimestamp(4,java.sql.Timestamp.valueOf(now));
            pstm.setString(5, booking.getNote());

            int rows = pstm.executeUpdate();
            System.out.println(rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void UpdateBooking(Booking booking) {
        try (Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "UPDATE bookings SET service_id = ?, room_id = ?, pet_id = ?, createTime = ?, note = ? WHERE id = ?";

            //lay thoi gian hien tai
            java.time.LocalDateTime now = java.time.LocalDateTime.now();

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setLong(1, booking.getServiceId());
            pstm.setLong(2, booking.getRoomId());
            pstm.setLong(3, booking.getPetId());
            // thoi gian tao phieu
            pstm.setTimestamp(4,java.sql.Timestamp.valueOf(now));
            pstm.setString(5, booking.getNote());
            pstm.setLong(6,booking.getId());

            int rows = pstm.executeUpdate();
            System.out.println(rows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
