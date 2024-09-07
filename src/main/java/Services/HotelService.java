package Services;

import Entities.Hotel;
import repositories.hotel.HotelRepository;
import repositories.hotel.HotelRepositoryImpl;

public class HotelService {
    private HotelRepository hotelRepository;

    // Constructor to initialize HotelRepository
    public HotelService() {
        this.hotelRepository = new HotelRepositoryImpl(); // Initialize with an instance
    }
    public void create(Hotel hotel) {
        hotelRepository.create(hotel);
    }
}
