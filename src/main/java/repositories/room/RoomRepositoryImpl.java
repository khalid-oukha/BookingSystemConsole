package repositories.room;

import Entities.Hotel;
import Entities.Room;
import Enums.Availability;
import Enums.RoomType;
import org.example.configuration.DatabaseConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private Connection connection;
    public RoomRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
    }

    @Override
    public void create(Room room, Hotel hotel) {
        String sql = "INSERT INTO rooms (number,price, availability, type, hotel_id) VALUES (?,?, CAST(? AS availability), CAST(? AS roomtype), ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, room.getNumber());
            statement.setDouble(2, room.getPrice());
            statement.setString(3, room.getAvailability().toString());
            statement.setString(4, room.getType().toString());
            statement.setInt(5, hotel.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error creating room: " + e.getMessage());
        }
    }


    @Override
    public Room findById(int id) {
        return null;
    }

    @Override
    public List<Room> findAll(Hotel hotel) {
        String sql = "SELECT * FROM rooms WHERE hotel_id = ?";
        List<Room> rooms = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotel.getId());

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int number = resultSet.getInt("number");
                    double price = resultSet.getDouble("price");
                    Availability availability = Availability.valueOf(resultSet.getString("availability"));
                    RoomType type = RoomType.valueOf(resultSet.getString("type"));

                    Room room = new Room(number, price, availability, type, hotel);
                    rooms.add(room);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching rooms: " + e.getMessage());
        }
        return rooms;
    }


    @Override
    public void update(Room room) {

    }

    @Override
    public void delete(Room room) {

    }
}
