package repositories.hotel;

import Entities.Hotel;

public interface HotelRepository {

    public Hotel findById(int id);
    public void create(Hotel hotel);
}
