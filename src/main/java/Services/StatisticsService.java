package Services;

import Entities.Client;
import Entities.Hotel;
import Entities.Reservation;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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

    public double yearIncome(Hotel hotel) {
        LinkedList<Reservation> reservations = reservationService.getAllReservations(hotel);
        return reservations.stream().filter(reservation -> reservation.getDate().getStartDate().getYear() == LocalDate.now().getYear())
                .mapToDouble(Reservation::getTotalPrice).sum();

    }

    public Client bestClient(Hotel hotel) {
        LinkedList<Reservation> reservations = reservationService.getAllReservations(hotel);
        HashMap<Client, Integer> clientReservationsCount = new HashMap<>();

        reservations.stream().forEach(reservation -> {
            Client client = reservation.getClient();
            clientReservationsCount.put(client, clientReservationsCount.getOrDefault(client, 0) + 1);
        });

        return clientReservationsCount.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse(null);
    }
}
