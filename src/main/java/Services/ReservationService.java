package Services;

import Entities.Hotel;
import Entities.Reservation;
import repositories.reservation.ReservationRepository;
import repositories.reservation.ReservationRepositoryImpl;

import java.util.LinkedList;

public class ReservationService {
    private final ReservationRepository reservationRepository;

    public ReservationService() {
        this.reservationRepository = new ReservationRepositoryImpl();
    }

    public LinkedList<Reservation> getAllReservations(Hotel hotel) {
        return reservationRepository.findAllReservations(hotel);
    }
}
