package repositories.hotel;

import Entities.Hotel;

import java.util.HashMap;

public interface HotelRepository {

    public Hotel findById(int id);
    public void create(Hotel hotel);
    public HashMap<Integer, Hotel> findAll();
}
