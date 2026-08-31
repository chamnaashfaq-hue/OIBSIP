package DAO ;

import Model.train;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainDAO {

    public String getTrainNameByNumber(String trainNumber) {
        String sql = "SELECT train_name FROM trains WHERE train_number = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, trainNumber);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("train_name");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
}