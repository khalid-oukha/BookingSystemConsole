package org.example;

import Entities.Hotel;
import Handlers.HotelHandler;
import Handlers.RoomHandler;

import java.util.Scanner;

public class HotelManagementMenu {
    private Scanner scanner;

    HotelHandler hotelHandler = new HotelHandler();
    RoomHandler roomHandler = new RoomHandler();
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
            System.out.println("=    4. Delete Room from the Hotel                                                 ");
            System.out.println("=    5. Search for available Rooms                                                   ");
            System.out.println("=    6. View All Reservation                                                      ");
            System.out.println("=    7. Make A Reservation                                                    ");
            System.out.println("=    8. Update Reservation                                                 ");
            System.out.println("=    9. Cancel Reservation                                                    ");
            System.out.println("=    10. Delete Hotel                                                       ");
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
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
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