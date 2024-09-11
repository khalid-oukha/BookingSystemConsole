package Services;

import Entities.Hotel;
import Entities.Reservation;
import Entities.Room;
import Enums.Availability;
import repositories.reservation.ReservationRepository;
import repositories.reservation.ReservationRepositoryImpl;

import java.util.LinkedList;

public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;
    private final PricingService pricingService;

    public ReservationService() {
        this.reservationRepository = new ReservationRepositoryImpl();
        this.roomService = new RoomService();
        this.pricingService = new PricingService();
    }

    public LinkedList<Reservation> getAllReservations(Hotel hotel) {
        return reservationRepository.findAllReservations(hotel);
    }

    public boolean saveReservation(Reservation reservation, Hotel hotel) {
        Room room = roomService.findById(reservation.getRoom().getNumber(), hotel);

        if (room != null && room.getAvailability() == Availability.AVAILABLE) {

            double totalPrice = pricingService.calculatePrice(reservation.getRoom(), reservation);
            reservation.setTotalPrice(totalPrice);

            if (reservationRepository.saveReservation(reservation, hotel)) {

                roomService.updateRoomAvailability(reservation.getRoom().getNumber(), Availability.NOT_AVAILABLE, hotel);

                return true;
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

    public boolean updateReservation(Reservation updatedReservation, Hotel hotel) {
        Reservation oldReservation = findReservationById(hotel, updatedReservation.getId());

        if (oldReservation != null && oldReservation.getRoom().getAvailability() == Availability.AVAILABLE) {
            if (oldReservation.getRoom().getNumber() != updatedReservation.getRoom().getNumber()) {
                roomService.updateRoomAvailability(oldReservation.getRoom().getNumber(), Availability.AVAILABLE, hotel);
                roomService.updateRoomAvailability(updatedReservation.getRoom().getNumber(), Availability.NOT_AVAILABLE, hotel);
            }

            double totalPrice = pricingService.calculatePrice(updatedReservation.getRoom(), updatedReservation);
            updatedReservation.setTotalPrice(totalPrice);

            return reservationRepository.updateReservation(updatedReservation, hotel);
        }
        return false;
    }

}
