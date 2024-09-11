package repositories.reservation;

import Entities.Hotel;
import Entities.Reservation;

import java.util.LinkedList;

public interface ReservationRepository {

    LinkedList<Reservation> findAllReservations(Hotel hotel);

    Boolean saveReservation(Reservation reservation, Hotel hotel);

    Boolean cancelReservation(Reservation reservation);

    Reservation findReservationById(int id, Hotel hotel);
}
