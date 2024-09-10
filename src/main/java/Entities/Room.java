package Entities;

import Enums.RoomType;
import Enums.Availability;


public class Room {
    private int number;
    private double price;
    private RoomType type;
    private Availability availability;
    private Hotel hotel;

    public Room(int number, double price, RoomType type, Hotel hotel) {
        this.number = number;
        this.price = price;
        this.type = type;
        this.hotel = hotel;
        this.availability = Availability.AVAILABLE;
    }

    public Room(int number, double price, Availability availability, RoomType type, Hotel hotel) {
        this.number = number;
        this.price = price;
        this.availability = availability;
        this.type = type;
        this.hotel = hotel;
    }

    public int getNumber() {
        return number;
    }

    public double getPrice() {
        return price;
    }

    public Availability getAvailability() {
        return availability;
    }

    public RoomType getType() {
        return type;
    }

    public Hotel getHotel() {
        return hotel;
    }

    @Override
    public String toString() {
        return "-----------------------------\n" +
                "Room Details:\n" +
                "-----------------------------\n" +
                "Room Number   : " + number + "\n" +
                "Price         : $" + price + "\n" +
                "Availability  : " + availability  + "\n" +
                "-----------------------------";
    }
}
