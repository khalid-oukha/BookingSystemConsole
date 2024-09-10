package org.example;

import Entities.Hotel;
import Handlers.HotelHandler;
import Handlers.ReservationHandler;
import Handlers.RoomHandler;

import java.util.Scanner;

public class HotelManagementMenu {
    private final Scanner scanner;

    HotelHandler hotelHandler = new HotelHandler();
    RoomHandler roomHandler = new RoomHandler();
    ReservationHandler reservationHandler = new ReservationHandler();

    public HotelManagementMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu(Hotel hotel) {
        int choice;
        do {
            System.out.println("================================================================================================");
            System.out.println("=                                  Manage " + hotel.getHotelName() + " Hotel Menu              ");
            System.out.println("================================================================================================");
            System.out.println("=    1. Update Hotel Name                                                            ");
            System.out.println("=    2. View All Hotel Rooms                                                         ");
            System.out.println("=    3. Add Room to the Hotel                                                         ");
            System.out.println("=    4. Find Room from the Hotel                                                 ");
            System.out.println("=    5. Delete Hotel Room                                                ");
            System.out.println("=    6. Update Hotel Room                                                  ");
            System.out.println("=    7. Search for available Rooms                                                   ");
            System.out.println("=    8. View All Reservation                                                      ");
            System.out.println("=    9. Make A Reservation                                                  ");
            System.out.println("=    10. Update Reservation                                                 ");
            System.out.println("=    11. Cancel Reservation                                                    ");
            System.out.println("=    12. Delete Hotel                                                       ");
            System.out.println("=    0. Return to Main Menu                                                 ");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    hotelHandler.update(hotel);
                    break;
                case 2:
                    roomHandler.findAll(hotel);
                    break;
                case 3:
                    roomHandler.create(hotel);
                    break;
                case 4:
                    roomHandler.findById(hotel);
                    break;
                case 5:
                    roomHandler.delete(hotel);
                    break;
                case 6:
                    roomHandler.update(hotel);
                    break;
                case 7:
                    roomHandler.getAvailableRooms(hotel);
                    break;
                case 8:
                    reservationHandler.getAllReservations(hotel);
                    break;
                case 9:
                    reservationHandler.saveReservation(hotel);
                    break;
                case 10:
                    hotelHandler.delete(hotel);
                    break;
                case 11:
                    System.out.println("Returning to main menu.");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
}