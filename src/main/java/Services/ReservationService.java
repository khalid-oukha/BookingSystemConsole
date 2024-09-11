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
            if (reservation.getRoom().getAvailability() == Availability.AVAILABLE) {
                if (reservationRepository.saveReservation(reservation, hotel)) {
                    roomService.updateRoomAvailability(reservation.getRoom().getNumber(), Availability.NOT_AVAILABLE, hotel);
                    return true;
                }
            } else {
                return false;
            }

        }
        return false;
    }

    public Reservation findReservationById(Hotel hotel, int id) {
        return reservationRepository.findReservationById(id, hotel);
    }

    public boolean deleteReservation(Hotel hotel, int id) {
        Reservation reservation = findReservationById(hotel, id);
        if (reservation != null && reservationRepository.cancelReservation(reservation)) {
            roomService.updateRoomAvailability(reservation.getRoom().getNumber(), Availability.AVAILABLE, hotel);
            return true;
        }
        return false;
    }
}
