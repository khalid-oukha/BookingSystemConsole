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
        HotelHandler hotelHandler = new HotelHandler();
        HotelManagementMenu hotelManagementMenu = new HotelManagementMenu(scanner);
        int choice;

        do {
            System.out.println("================================================================================================");
            System.out.println("=                                  Hotel Management Menu                                       =");
            System.out.println("================================================================================================");
            System.out.println("=    1. Create a New Hotel                                                                      =");
            System.out.println("=    2. Show All Hotels                                                                         =");
            System.out.println("=    3. Manage Existing Hotel                                                                   =");
            System.out.println("================================================================================================");
            System.out.print("=    Please Enter Your Option: ");

            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    hotelHandler.create();
                    break;
                case 2:
                    hotelHandler.findAll();
                    break;
                case 3:
                    Hotel hotel = hotelHandler.findById();
                    hotelManagementMenu.displayMenu(hotel);
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