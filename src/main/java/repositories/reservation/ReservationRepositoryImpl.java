package repositories.reservation;

import Entities.Client;
import Entities.Hotel;
import Entities.Reservation;
import Entities.Room;
import commons.DateInterval;
import org.example.configuration.DatabaseConfig;
import repositories.room.RoomRepository;
import repositories.room.RoomRepositoryImpl;

import java.sql.*;
import java.util.LinkedList;

public class ReservationRepositoryImpl implements ReservationRepository {

    private Connection connection;
    private final RoomRepository roomRepository;

    public ReservationRepositoryImpl() {
        try {
            this.connection = DatabaseConfig.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Error establishing connection: " + e.getMessage());
        }
        this.roomRepository = new RoomRepositoryImpl();
    }

    @Override
    public LinkedList<Reservation> findAllReservations(Hotel hotel) {
        String sql = "SELECT rv.*, c.*, r.* FROM reservations rv " +
                "INNER JOIN rooms r ON rv.room_number = r.number " +
                "INNER JOIN clients c ON rv.client_cin = c.cin " +
                "WHERE r.hotel_id = ?";


        LinkedList<Reservation> reservations = new LinkedList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, hotel.getId());
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int roomNumber = resultSet.getInt("room_number");
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

    @Override
    public Boolean saveReservation(Reservation reservation, Hotel hotel) {
        String sql = "INSERT INTO reservations (room_number, client_cin, date_start, date_end,number_of_days) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getRoom().getNumber());
            statement.setString(2, reservation.getClient().getCin());
            statement.setDate(3, Date.valueOf(reservation.getDate().getStartDate()));
            statement.setDate(4, Date.valueOf(reservation.getDate().getEndDate()));
            statement.setLong(5, reservation.getNumberOfDays());

            if (statement.executeUpdate() == 1) {
                return true;
            }

        } catch (SQLException e) {
            System.out.println("Error saving reservation: " + e.getMessage());
        }
        return false;
    }

    public Boolean cancelReservation(Reservation reservation) {
        String sql = "DELETE FROM reservations WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getId());

            if (statement.executeUpdate() == 1) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting reservation: " + e.getMessage());
        }
        return false;
    }

    public Reservation findReservationById(int id, Hotel hotel) {
        String sql = "SELECT * FROM reservations r inner join clients c on r.client_cin = c.cin  WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int roomNumber = resultSet.getInt("room_number");
                Date startDate = resultSet.getDate("date_start");
                Date endDate = resultSet.getDate("date_end");

                Room room = roomRepository.findById(roomNumber, hotel);

                Client client = new Client(
                        resultSet.getString("cin"),
                        resultSet.getString("full_name")
                );

                DateInterval dateInterval = new DateInterval(startDate.toLocalDate(), endDate.toLocalDate());
                return new Reservation(id, client, room, dateInterval);
            }

        } catch (SQLException e) {
            System.out.println("Error deleting reservation: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Boolean updateReservation(Reservation reservation, Hotel hotel) {
        String sql = "UPDATE reservations SET room_number = ?, client_cin = ?, date_start = ?, date_end = ?, number_of_days = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, reservation.getRoom().getNumber());
            statement.setString(2, reservation.getClient().getCin());
            statement.setDate(3, Date.valueOf(reservation.getDate().getStartDate()));
            statement.setDate(4, Date.valueOf(reservation.getDate().getEndDate()));
            statement.setLong(5, reservation.getNumberOfDays());
            statement.setInt(6, reservation.getId());

            return statement.executeUpdate() == 1;
        } catch (SQLException e) {
            System.out.println("Error updating reservation: " + e.getMessage());
        }
        return false;
    }


}
