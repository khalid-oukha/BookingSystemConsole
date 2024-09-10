package Handlers;

import Entities.Hotel;
import Entities.Reservation;
import Services.ReservationService;

import java.util.LinkedList;
import java.util.Scanner;

public class ReservationHandler {
    private ReservationService reservationService;
    private final Scanner scanner = new Scanner(System.in);

    public ReservationHandler() {
        reservationService = new ReservationService();
    }

    public void getAllReservations(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Hotels List                                          =");
        System.out.println("================================================================================================");

        LinkedList<Reservation> reservations = reservationService.getAllReservations(hotel);
        if (reservations.isEmpty()) {
            System.out.println("No reservations found for this hotel.");
        } else {
            for (Reservation reservation : reservations) {
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("Reservation ID: " + reservation.getId());
                System.out.println("Client CIN    : " + reservation.getClient().getCin());
                System.out.println("Client Name   : " + reservation.getClient().getFullName());
                System.out.println("Room Number   : " + reservation.getRoom().getNumber());
                System.out.println("Room Type     : " + reservation.getRoom().getType());
                System.out.println("Start Date    : " + reservation.getDate().getStartDate());
                System.out.println("End Date      : " + reservation.getDate().getEndDate());
                System.out.println("Days Reserved : " + reservation.getNumberOfDays());
                System.out.println("------------------------------------------------------------------------------------------");
            }
        }
    }
}
