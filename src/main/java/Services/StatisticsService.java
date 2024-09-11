package Services;

import Entities.Hotel;
import Entities.Reservation;

import java.time.LocalDate;
import java.util.LinkedList;

public class StatisticsService {
    private RoomService roomService;
    private ReservationService reservationService;

    public StatisticsService() {
        roomService = new RoomService();
        reservationService = new ReservationService();
    }

    public double monthlyIncome(Hotel hotel) {
        LinkedList<Reservation> reservations = reservationService.getAllReservations(hotel);
        return reservations.stream().filter(reservation -> reservation.getDate().getStartDate().getMonth() == LocalDate.now().getMonth())
                .mapToDouble(Reservation::getTotalPrice).sum();

    }
}
