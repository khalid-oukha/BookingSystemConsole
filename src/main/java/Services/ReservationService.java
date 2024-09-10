package Services;

import Entities.Hotel;
import Entities.Reservation;
import Enums.Availability;
import repositories.reservation.ReservationRepository;
import repositories.reservation.ReservationRepositoryImpl;

import java.util.LinkedList;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;

    public ReservationService() {
        this.reservationRepository = new ReservationRepositoryImpl();
        this.roomService = new RoomService();
    }

    public LinkedList<Reservation> getAllReservations(Hotel hotel) {
        return reservationRepository.findAllReservations(hotel);
    }

    public boolean saveReservation(Reservation reservation, Hotel hotel) {
        if (reservation.getRoom().getAvailability() == Availability.AVAILABLE && roomService.findById(reservation.getRoom().getNumber(), hotel) != null) {
            if (reservationRepository.saveReservation(reservation, hotel)) {
                roomService.updateRoomAvailability(reservation.getRoom().getNumber(), Availability.NOT_AVAILABLE, hotel);
                return true;
            }
        }
        return false;
    }
}
