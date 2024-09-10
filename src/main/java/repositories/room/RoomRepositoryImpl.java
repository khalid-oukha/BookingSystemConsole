package repositories.room;

import Entities.Hotel;
import Entities.Room;
import Enums.Availability;
import Enums.RoomType;
import org.example.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomRepositoryImpl implements RoomRepository {

    private Connection connection;

    public RoomRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
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
    public Room findById(int roomNumber, Hotel hotel) {
        String sql = "SELECT * FROM rooms WHERE number = ? AND hotel_id = ?";
        Room room = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomNumber);
            statement.setInt(2, hotel.getId());
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int number = resultSet.getInt("number");
                double price = resultSet.getDouble("price");
                Availability availability = Availability.valueOf(resultSet.getString("availability"));
                RoomType type = RoomType.valueOf(resultSet.getString("type"));

                room = new Room(number, price, availability, type, hotel);
            }

        } catch (SQLException e) {
            System.out.println("Error finding room: " + e.getMessage());
        }
        return room;
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
    public boolean update(Room room) {
        String sql = "UPDATE rooms SET price = ?, availability = CAST(? AS availability), type = CAST(? AS roomtype) WHERE number = ? AND hotel_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setDouble(1, room.getPrice());
            statement.setString(2, room.getAvailability().toString());
            statement.setString(3, room.getType().toString());
            statement.setInt(4, room.getNumber());
            statement.setInt(5, room.getHotel().getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating room: " + e.getMessage());
        }
        return false;
    }


    @Override
    public boolean delete(int roomNumber) {
        String sql = "DELETE FROM rooms WHERE number = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, roomNumber);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting room: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateRoomAvailability(int roomNumber, Availability availability, Hotel hotel) {
        String sql = "UPDATE rooms SET availability = CAST(? AS availability) WHERE number = ? AND hotel_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, availability.toString());
            statement.setInt(2, roomNumber);
            statement.setInt(3, hotel.getId());

            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Error updating room availability: " + e.getMessage());
        }
        return false;
    }
}
