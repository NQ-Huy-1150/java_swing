package com.java_swing_project.main.java.service;

import com.java_swing_project.main.java.domain.Booking;
import com.java_swing_project.main.java.domain.Service;
import com.java_swing_project.main.java.repository.BookingRepository;
import com.java_swing_project.main.java.repository.MssSQLConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookingService {
    private final BookingRepository bookingRepository;
    private final MssSQLConnection mssSQLConnection;

    public BookingService() {
        bookingRepository = new BookingRepository();
        mssSQLConnection = new MssSQLConnection();

    }
    public List<Service> getAllServiceFromDb() {
        return bookingRepository.getAllService();
    }
    public Service getServiceByName(String name) {
        Service service = new Service();
        try(Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM services WHERE \"name\" = \'" + name + "\'";
            System.out.println(sql);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                service.setId(rs.getLong("id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return service;
    }
    public Service getServiceById(long id) {
        Service service = new Service();
        try(Connection connection = mssSQLConnection.dbConnection()) {
            String sql = "SELECT * FROM services WHERE id = " + id ;
            System.out.println(sql);
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                service.setId(rs.getLong("id"));
                service.setName(rs.getString("name"));
                service.setDescription(rs.getString("description"));
                service.setPrice(rs.getDouble("price"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return service;
    }

    public List<Booking> getAllBookingFromDb() {
        return this.bookingRepository.getAllBooking();
    }

    public void getBookingDelete(long id) {
        this.bookingRepository.deleteBookingById(id);
    }

    public void CreateNewBooking(Booking booking) {
        this.bookingRepository.createBooking(booking);
    }
    public Booking getBookingById(long id) {
        return this.bookingRepository.findBookingById(id);
    }
    public void getUpdateBooking(Booking booking) {
        this.bookingRepository.UpdateBooking(booking);
    }
}
