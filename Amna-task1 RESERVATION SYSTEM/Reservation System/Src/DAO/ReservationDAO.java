package DAO;

import Model.reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReservationDAO {

    public int bookReservation(reservation res) {
        String sql = "INSERT INTO reservations (passenger_name, train_number, train_name, class_type, journey_date, source_station, destination_station) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, res.getPassengerName());
            stmt.setString(2, res.getTrainNumber());
            stmt.setString(3, res.getTrainName());
            stmt.setString(4, res.getClassType());
            stmt.setString(5, res.getJourneyDate());
            stmt.setString(6, res.getSourceStation());
            stmt.setString(7, res.getDestinationStation());

            int rows = stmt.executeUpdate();

            if (rows > 0) {
                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public reservation getReservationByPnr(int pnr) {
        String sql = "SELECT * FROM reservations WHERE pnr = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pnr);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                reservation res = new reservation();
                res.setPnr(rs.getInt("pnr"));
                res.setPassengerName(rs.getString("passenger_name"));
                res.setTrainNumber(rs.getString("train_number"));
                res.setTrainName(rs.getString("train_name"));
                res.setClassType(rs.getString("class_type"));
                res.setJourneyDate(rs.getString("journey_date"));
                res.setSourceStation(rs.getString("source_station"));
                res.setDestinationStation(rs.getString("destination_station"));
                return res;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean cancelReservation(int pnr) {
        String sql = "DELETE FROM reservations WHERE pnr = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, pnr);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}