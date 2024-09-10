package repositories.reservation;

import Entities.Hotel;
import Entities.Reservation;
import org.example.configuration.DatabaseConfig;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;

public interface ReservationRepository {

    LinkedList<Reservation> findAllReservations(Hotel hotel);
}
