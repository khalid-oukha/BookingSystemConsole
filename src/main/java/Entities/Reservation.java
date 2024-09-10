package Entities;

import commons.DateInterval;

public class Reservation {
    private int id;
    private Client client;
    private Room room;
    private DateInterval date;
    private long numberOfDays;

    public Reservation(Client client, Room room, DateInterval date) {
        this.client = client;
        this.room = room;
        this.date = date;
        this.numberOfDays = date.getNumberOfDays(date.getStartDate(), date.getEndDate());
    }

    public Reservation(int id, Client client, Room room, DateInterval date) {
        this.id = id;
        this.client = client;
        this.room = room;
        this.date = date;
        this.numberOfDays = date.getNumberOfDays(date.getStartDate(), date.getEndDate());
    }

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

}
