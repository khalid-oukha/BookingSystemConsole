package repositories.hotel;

import Entities.Hotel;
import org.example.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

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
        String sql = "SELECT * FROM hotels WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String name = resultSet.getString("name");
                Hotel hotel = new Hotel(name);
                hotel.setId(id);
                return hotel;
            } else {
                System.out.println("No hotel found with ID: " + id);
            }
        } catch (SQLException e) {
            System.out.println("Error finding hotel: " + e.getMessage());
        }
        return null;
    }


    @Override
    public void create(Hotel hotel) {
        String sql = "insert into hotels (name) values (?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,hotel.getHotelName());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error creating hotel: " + e.getMessage());
        }
    }

    @Override
    public HashMap<Integer, Hotel> findAll() {
        String sql = "SELECT * FROM hotels";
        HashMap<Integer, Hotel> hotels = new HashMap<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                Hotel hotel = new Hotel(name);
                hotel.setId(id);

                hotels.put(id, hotel);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching hotels: " + e.getMessage());
        }

        return hotels;
    }

    @Override
    public void update(Hotel hotel) {
        String sql = "UPDATE hotels SET name = ? WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1,hotel.getHotelName());
            statement.setInt(2,hotel.getId());
            statement.executeUpdate();
            System.out.println("Hotel updated successfully");
        }catch (SQLException e) {
            System.out.println("Error updating hotel: " + e.getMessage());
        }
    }

    @Override
    public void delete(Hotel hotel)
    {
        String sql = "DELETE FROM hotels WHERE id = ?";
        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1,hotel.getId());
            statement.executeUpdate();
            System.out.println("Hotel deleted successfully");
        }catch (SQLException e) {
            System.out.println("Error deleting hotel: " + e.getMessage());
        }
    }
}
