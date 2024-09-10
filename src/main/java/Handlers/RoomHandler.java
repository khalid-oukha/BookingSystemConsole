package Handlers;

import Entities.Hotel;
import Entities.Room;
import Enums.Availability;
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
        System.out.println("=                                    Create Room                                          =");
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

    public void findById(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Find Room by number                                          =");
        System.out.println("================================================================================================");

        System.out.print("Enter Room Number : ");
        int number = scanner.nextInt();
        Room room = roomService.findById(number,hotel);
        if (room !=null) {
            System.out.println(" | Room : " + room.getNumber() + " | Price : " + room.getPrice() + " | Type : " + room.getType() + " | Available : " + room.getAvailability());
        }else {
            System.out.println("No Room found.");
        }
    }

    public void delete(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Delete Room by Number                                       =");
        System.out.println("================================================================================================");

        System.out.print("Enter Room Number: ");
        int number = scanner.nextInt();

        boolean isDeleted = roomService.delete(number, hotel);

        if (isDeleted) {
            System.out.println("Room " + number + " successfully deleted.");
        } else {
            System.out.println("Failed to delete room " + number + ".");
        }
    }

    public void getAvailableRooms(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Available Rooms                                       =");
        System.out.println("================================================================================================");
        HashMap<Integer,Room> availableRooms = new HashMap<>();
        availableRooms = roomService.getAvailableRooms(hotel);

        for (Room room : availableRooms.values()) {
            System.out.println("Room : " + room.getNumber() + " | Price : " + room.getPrice() + " | Type : " + room.getType());
        }
    }

    public void update(Hotel hotel) {
        System.out.println("================================================================================================");
        System.out.println("=                                    Update Room                                       =");
        System.out.println("================================================================================================");

        System.out.print("Enter Room Number: ");
        int number = scanner.nextInt();
        scanner.nextLine();

        Room room = roomService.findById(number, hotel);
        if (room == null) {
            System.out.println("Room not found.");
            return;
        }

        System.out.println("Current Room Details:");
        System.out.println("Number: " + room.getNumber());
        System.out.println("Price: " + room.getPrice());
        System.out.println("Availability: " + room.getAvailability());
        System.out.println("Type: " + room.getType());

        System.out.print("Enter new price (or press Enter to keep current price): ");
        String newPrice = scanner.nextLine();
        if (!newPrice.isEmpty()) {
            room.setPrice(Double.parseDouble(newPrice));
        }

        System.out.println("Choose new availability:");
        System.out.println(" 0 - " + Availability.AVAILABLE);
        System.out.println(" 1 - " + Availability.NOT_AVAILABLE);
        int availabilityOption = scanner.nextInt();
        scanner.nextLine();
        Availability newAvailability = Availability.values()[availabilityOption];
        room.setAvailability(newAvailability);

        System.out.println("Choose new room type:");
        System.out.println(" 0 - " + RoomType.SINGLE);
        System.out.println(" 1 - " + RoomType.DOUBLE);
        System.out.println(" 2 - " + RoomType.SUITE);
        int roomTypeOption = scanner.nextInt();
        scanner.nextLine();
        RoomType newRoomType = RoomType.values()[roomTypeOption];
        room.setType(newRoomType);

        boolean isUpdated = roomService.update(room, hotel);
        if (isUpdated) {
            System.out.println("Room updated successfully.");
        } else {
            System.out.println("Room update failed.");
        }
    }


}
