package Handlers;

import Entities.Client;
import Entities.Hotel;
import Entities.Reservation;
import Entities.Room;
import Services.ClientService;
import Services.ReservationService;
import commons.DateInterval;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class ReservationHandler {
    private ReservationService reservationService;
    private final Scanner scanner = new Scanner(System.in);
    private RoomHandler roomHandler;
    private ClientService clientService;

    public ReservationHandler() {
        reservationService = new ReservationService();
        clientService = new ClientService();
        roomHandler = new RoomHandler();
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

    public void saveReservation(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Save Reservation                                       =");
        System.out.println("================================================================================================");
        roomHandler.findAll(hotel);
        Room room = roomHandler.findById(hotel);
        if (room != null) {
            System.out.print("Enter Client CIN: ");
            String clientCin = scanner.nextLine();
            System.out.print("Enter Client Name: ");
            String clientName = scanner.nextLine();

            Client client = clientService.findClientByCin(clientCin);
            if (client == null) {
                client = new Client(clientCin, clientName);
                clientService.addClient(client);
            }

            System.out.println("Enter Start Date YYYY-MM-dd: ");
            LocalDate startDate = LocalDate.parse(scanner.nextLine());
            System.out.print("Enter End Date YYYY-MM-dd: ");
            LocalDate endDate = LocalDate.parse(scanner.nextLine());

            DateInterval dateInterval = new DateInterval(startDate, endDate);

            Reservation reservation = new Reservation(client, room, dateInterval);

            if (reservationService.saveReservation(reservation, hotel)) {
                System.out.println("Reservation has been saved successfully.");
            } else {
                System.out.println("Reservation could not be saved.");
            }
        } else {
            System.out.println("No room found for this hotel.");
        }
    }

}
