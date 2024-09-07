package org.example;


import Entities.Hotel;
import Entities.Room;
import Enums.Availability;
import Enums.RoomType;
import Handlers.HotelHandler;
import Services.HotelService;
import Services.RoomService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HotelService hotelService = new HotelService(); // Ensure this is initialized
        HotelHandler hotelHandler = new HotelHandler(hotelService); // Pass it to the handler

        int choice;

        do {
            System.out.println("================================================================================================");
            System.out.println("=                                  Hotel Management Menu                                       =");
            System.out.println("================================================================================================");
            System.out.println("=    1. Create a New Hotel                                                                      =");
            System.out.println("=    2. Show All Hotels                                                                         =");
            System.out.println("=    3. Manage Existing Hotel                                                                   =");
            System.out.println("=    0. Return to Main Menu                                                                     =");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotelHandler.create();
                    System.out.println("Creating new hotel");
                    break;
                case 2:

                    break;
                case 3:

                    System.out.println("Adding a new reservation...");
                    break;
                case 4:
                    // Cancel reservation logic
                    System.out.println("Canceling a reservation...");
                    break;
                case 5:
                    // Modify reservation logic
                    System.out.println("Modifying a reservation...");
                    break;
                case 6:
                    // Search reservation logic
                    System.out.println("Searching for a reservation...");
                    break;
                case 0:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (choice != 0);
    }
}