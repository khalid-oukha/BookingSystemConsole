package repositories.hotel;

import Entities.Hotel;

import java.util.HashMap;

public interface HotelRepository {

    Hotel findById(int id);
    void create(Hotel hotel);
    HashMap<Integer, Hotel> findAll();
    void update(Hotel hotel);
    void delete(Hotel hotel);

}
