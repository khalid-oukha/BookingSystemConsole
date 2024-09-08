package Services;

import Entities.Hotel;
import Entities.Room;
import repositories.room.RoomRepository;
import repositories.room.RoomRepositoryImpl;

import java.util.List;

public class RoomService {
    private RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepositoryImpl();
    }

    public List<Room> findAll(Hotel hotel) {
        return roomRepository.findAll(hotel);
    }

    public void create(Room room, Hotel hotel) {
        roomRepository.create(room,hotel);
    }
}
