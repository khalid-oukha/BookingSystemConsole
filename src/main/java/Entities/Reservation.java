package Entities;

import commons.DateInterval;

public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private DateInterval date;
    private long numberOfDays;
    private double totalPrice;

    public Reservation(Client client, Room room, DateInterval date) {
        this.client = client;
        this.room = room;
        this.date = date;
        this.numberOfDays = date.getNumberOfDays(date.getStartDate(), date.getEndDate());
    }

    public Reservation(int id, Client client, Room room, DateInterval date, double totalPrice) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.date = date;
        this.numberOfDays = date.getNumberOfDays(date.getStartDate(), date.getEndDate());
        this.totalPrice = totalPrice;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Room getRoom() {
        return room;
    }

    public DateInterval getDate() {
        return date;
    }

    public long getNumberOfDays() {
        return numberOfDays;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setDate(DateInterval date) {
        this.date = date;
        this.numberOfDays = date.getNumberOfDays(date.getStartDate(), date.getEndDate());
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
