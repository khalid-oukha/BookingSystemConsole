package Services;

import Entities.Hotel;
import repositories.hotel.HotelRepository;
import repositories.hotel.HotelRepositoryImpl;

import java.util.HashMap;

public class HotelService {
    private final HotelRepository hotelRepository;

    public HotelService() {
        this.hotelRepository = new HotelRepositoryImpl();
    }
    public void create(Hotel hotel) {
        hotelRepository.create(hotel);
    }

    public HashMap<Integer, Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(int id) {
        return hotelRepository.findById(id);
    }

    public Hotel update(Hotel hotel) {
        hotelRepository.update(hotel);
        return hotel;
    }

    public void delete(Hotel hotel) {
        hotelRepository.delete(hotel);
    }
}
