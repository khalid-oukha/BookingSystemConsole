package Handlers;

import Entities.Hotel;
import Entities.Room;
import Enums.RoomType;
import Services.RoomService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class RoomHandler {
    private RoomService roomService;
    private Scanner scanner = new Scanner(System.in);

    public RoomHandler() {
        this.roomService = new RoomService();
    }

    public void findAll(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Room List                                          =");
        System.out.println("================================================================================================");

        List<Room> rooms;
        rooms = roomService.findAll(hotel);
        if(rooms.isEmpty()){
            System.out.println("No Rooms found.");
        }else {
            for (Room room : rooms) {
                System.out.println(" | Room : " + room.getNumber() + " | Price : " + room.getPrice() + " | Type : " + room.getType() + " | Available : " + room.getAvailability());
            }
        }
    }

    public void create(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Room List                                          =");
        System.out.println("================================================================================================");

        System.out.print("Enter Room Number : ");
        int number = scanner.nextInt();

        System.out.print("Enter Room price : ");
        double price = scanner.nextDouble();

        System.out.print("Choose Room type  : ");
        System.out.println(" 0 - " + RoomType.SINGLE);
        System.out.println(" 1 - " + RoomType.DOUBLE);
        System.out.println(" 2 - " + RoomType.SUITE);

        int option = scanner.nextInt();
        RoomType type = RoomType.values()[option];

        Room room = new Room(number,price, type, hotel);
        roomService.create(room, hotel);
        System.out.println("Room Created Successfully");
    }
}
