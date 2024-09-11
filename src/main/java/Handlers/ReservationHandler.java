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
    private final ReservationService reservationService;
    private final Scanner scanner = new Scanner(System.in);
    private final RoomHandler roomHandler;
    private final ClientService clientService;

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
                System.out.println("Total price : " + reservation.getTotalPrice() + "DH");
                System.out.println("------------------------------------------------------------------------------------------");
            }
            System.out.println("All reservations : " + reservations.size());
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
                System.out.println("Reservation could not be saved Room not available.");
            }
        } else {
            System.out.println("No room found for this hotel.");
        }
    }

    public void cancelReservation(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Cancel Reservation                                       =");
        System.out.println("================================================================================================");

        System.out.print("Enter Reservation ID: ");
        int reservationID = scanner.nextInt();
        Reservation reservation = reservationService.findReservationById(hotel, reservationID);

        if (reservation != null) {
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Reservation ID: " + reservation.getId());
            System.out.println("Client CIN    : " + reservation.getClient().getCin());
            System.out.println("Client Name   : " + reservation.getClient().getFullName());
            System.out.println("Room Number   : " + reservation.getRoom().getNumber());
            System.out.println("Room Type     : " + reservation.getRoom().getType());
            System.out.println("Start Date    : " + reservation.getDate().getStartDate());
            System.out.println("End Date      : " + reservation.getDate().getEndDate());
            System.out.println("Days Reserved : " + reservation.getNumberOfDays());
            System.out.println("Total price : " + reservation.getRoom().getPrice() * reservation.getNumberOfDays() + "DH");
            System.out.println("------------------------------------------------------------------------------------------");

            System.out.println("Are you sure you want cancel reservation : ");
            System.out.println(" 1-YES ");
            System.out.println(" 2-NO ");
            if (scanner.nextInt() == 1) {
                if (reservationService.deleteReservation(hotel, reservationID)) {
                    System.out.println("Reservation has been cancelled successfully.");
                } else {
                    System.out.println("Reservation could not be cancelled.");
                }
            }
        }

    }

    public void updateReservation(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Update Reservation                                       =");
        System.out.println("================================================================================================");

        System.out.print("Enter Reservation ID: ");
        int reservationID = scanner.nextInt();
        Reservation reservation = reservationService.findReservationById(hotel, reservationID);

        if (reservation != null) {
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Reservation ID: " + reservation.getId());
            System.out.println("Client CIN    : " + reservation.getClient().getCin());
            System.out.println("Client Name   : " + reservation.getClient().getFullName());
            System.out.println("Room Number   : " + reservation.getRoom().getNumber());
            System.out.println("Room Type     : " + reservation.getRoom().getType());
            System.out.println("Start Date    : " + reservation.getDate().getStartDate());
            System.out.println("End Date      : " + reservation.getDate().getEndDate());
            System.out.println("Days Reserved : " + reservation.getNumberOfDays());
            System.out.println("Total price : " + reservation.getRoom().getPrice() * reservation.getNumberOfDays() + "DH");

            System.out.println("------------------------------------------------------------------------------------------");

            System.out.println("Enter new Start Date (YYYY-MM-dd) or press Enter to skip: ");
            scanner.nextLine();
            String newStartDate = scanner.nextLine();
            System.out.println("Enter new End Date (YYYY-MM-dd) or press Enter to skip: ");
            String newEndDate = scanner.nextLine();

            DateInterval dateInterval = reservation.getDate();

            if (!newStartDate.isEmpty() && !newEndDate.isEmpty()) {
                LocalDate startDate = LocalDate.parse(newStartDate);
                LocalDate endDate = LocalDate.parse(newEndDate);
                dateInterval = new DateInterval(startDate, endDate);
            }

            System.out.println("Do you want to change the room? (1-Yes / 2-No): ");
            int changeRoom = scanner.nextInt();
            Room newRoom = reservation.getRoom();

            if (changeRoom == 1) {
                roomHandler.findAll(hotel);
                newRoom = roomHandler.findById(hotel);
            }


            Reservation updatedReservation = new Reservation(reservation.getClient(), newRoom, dateInterval);

            if (reservationService.updateReservation(updatedReservation, hotel)) {
                System.out.println("Reservation has been updated successfully.");
            } else {
                System.out.println("Failed to update reservation Room not available.");
            }

        } else {
            System.out.println("Reservation not found.");
        }
    }


}
