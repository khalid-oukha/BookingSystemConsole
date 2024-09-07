package repositories.hotel;

import Entities.Hotel;
import org.example.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HotelRepositoryImpl implements HotelRepository {

    private Connection connection;
    public HotelRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }
    @Override
    public Hotel findById(int id) {
        return null;
    }

    @Override
    public void create(Hotel hotel) {
        String sql = "insert into hotels (name) values (?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,hotel.getHotelName());
            statement.executeUpdate();
            System.out.println("Hotel created successfully");
        } catch (SQLException e) {
            System.out.println("Error creating hotel: " + e.getMessage());
        }
    }
}
