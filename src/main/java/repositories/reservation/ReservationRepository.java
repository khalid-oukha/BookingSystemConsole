package repositories.reservation;

import Entities.Hotel;
import Entities.Reservation;

import java.util.LinkedList;

public interface ReservationRepository {

    LinkedList<Reservation> findAllReservations(Hotel hotel);

    Boolean saveReservation(Reservation reservation, Hotel hotel);
}
