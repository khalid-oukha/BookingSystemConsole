package repositories.reservation;

import Entities.Client;
import Entities.Hotel;
import Entities.Reservation;
import Entities.Room;
import Enums.Availability;
import Enums.RoomType;
import commons.DateInterval;
import org.example.configuration.DatabaseConfig;
import repositories.room.RoomRepository;
import repositories.room.RoomRepositoryImpl;

import java.sql.*;
import java.sql.SQLException;
import java.util.LinkedList;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Connection connection;
    private final RoomRepository roomRepository;

    public ReservationRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        }catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
        this.roomRepository = new RoomRepositoryImpl();
    }

    @Override
    public LinkedList<Reservation> findAllReservations(Hotel hotel) {
        String sql = " SELECT rv.*, c.* FROM reservations rv " +
                "INNER JOIN clients c " +
                "ON rv.client_cin = c.cin " +
                "WHERE r.hotel_id = ?";

        LinkedList<Reservation> reservations = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotel.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
                String clientCin = resultSet.getString("client_cin");
                Date startDate = resultSet.getDate("date_start");
                Date endDate = resultSet.getDate("date_end");

                Room room = roomRepository.findById(roomNumber, hotel);

                Client client = new Client(
                        resultSet.getString("cin"),
                        resultSet.getString("full_name")
                );

                DateInterval dateInterval = new DateInterval(startDate.toLocalDate(), endDate.toLocalDate());
                Reservation reservation = new Reservation(id, client, room, dateInterval);

                reservations.add(reservation);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching reservations: " + e.getMessage());
        }

        return reservations;
    }

}
