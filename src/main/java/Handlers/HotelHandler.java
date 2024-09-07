package Handlers;

import Entities.Hotel;
import Services.HotelService;

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
}
