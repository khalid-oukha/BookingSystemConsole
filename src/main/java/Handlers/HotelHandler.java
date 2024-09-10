package Handlers;

import Entities.Hotel;
import Helpers.StringValidator;
import Services.HotelService;

import java.util.HashMap;
import java.util.Scanner;

public class HotelHandler {

    private final HotelService hotelService;
    private final Scanner scanner = new Scanner(System.in);

    public HotelHandler() {
        this.hotelService = new HotelService();
    }

    public void create() {
        System.out.println("Enter hotel name:");
        String name = scanner.nextLine();

        Hotel hotel = new Hotel(name);
        hotelService.create(hotel);
        System.out.println("Hotel created successfully.");
    }

    public void findAll() {
        System.out.println("================================================================================================");
        System.out.println("=                                    Hotels List                                          =");
        System.out.println("================================================================================================");

        HashMap<Integer, Hotel> hotels = new HashMap<>();

        hotels = hotelService.findAll();
        if(hotels.isEmpty()){
            System.out.println("No Hotels found.");
        }else {
            for (Hotel hotel : hotels.values()) {
                System.out.println("ID: " + hotel.getId() + " - Name: " + hotel.getHotelName());
            }
        }
    }

    public Hotel findById() {
        System.out.println("Enter hotel ID :");
        int id = scanner.nextInt();
        Hotel hotel = hotelService.findById(id);
        if(hotel == null){
            System.out.println("Hotel not found.");
            return null;
        }else {
            return hotel;
        }
    }

    public Hotel update(Hotel hotel) {
        System.out.println("Enter hotel New Name:");
        String name = scanner.nextLine();

        if (StringValidator.isValidString(name)) {
            hotel.setHotelName(name);
            return hotelService.update(hotel);
        } else {
            System.out.println("Invalid name. It must be between 3 and 200 characters.");
            return null;
        }
    }

    public void delete(Hotel hotel) {
            hotelService.delete(hotel);
    }
}
