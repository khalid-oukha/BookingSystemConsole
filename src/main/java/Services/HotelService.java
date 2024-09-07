package Services;

import Entities.Hotel;
import repositories.hotel.HotelRepository;
import repositories.hotel.HotelRepositoryImpl;

public class HotelService {
    private HotelRepository hotelRepository;

    public HotelService() {
        this.hotelRepository = new HotelRepositoryImpl();
    }
    public void create(Hotel hotel) {
        hotelRepository.create(hotel);
    }
}
