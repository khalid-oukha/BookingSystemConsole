package Handlers;

import Entities.Hotel;
import Services.HotelService;

import java.util.HashMap;
import java.util.Scanner;

public class HotelHandler {

    private HotelService hotelService;
    private Scanner scanner = new Scanner(System.in);

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

    public void findById() {
        System.out.println("Enter hotel ID :");
        int id = scanner.nextInt();
        Hotel hotel = hotelService.findById(id);
        if(hotel == null){
            System.out.println("Hotel not found.");
        }else {
            System.out.println("Hotel name: " + hotel.getHotelName());
        }
    }
}
