package Services;

import Entities.Hotel;
import Entities.Room;
import Enums.Availability;
import repositories.room.RoomRepository;
import repositories.room.RoomRepositoryImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class RoomService {
    private final RoomRepository roomRepository;

    public RoomService() {
        this.roomRepository = new RoomRepositoryImpl();
    }

    public List<Room> findAll(Hotel hotel) {
        return roomRepository.findAll(hotel);
    }

    public void create(Room room, Hotel hotel) {
        roomRepository.create(room,hotel);
    }

    public Room findById(int roomNumber, Hotel hotel) {
        return roomRepository.findById(roomNumber, hotel);
    }

    public boolean delete(int roomNumber, Hotel hotel) {
        Room room = roomRepository.findById(roomNumber, hotel);
        if (room != null) {
            return roomRepository.delete(roomNumber);
        } else {
            System.out.println("Room not found.");
            return false;
        }
    }

    public HashMap<Integer,Room> getAvailableRooms(Hotel hotel) {
        return findAll(hotel).stream()
                .filter(room -> room.getAvailability() == Availability.AVAILABLE)
                .collect(Collectors.toMap(Room::getNumber, room -> room, (oldValue, newValue) -> oldValue, HashMap::new));
    }

    public boolean update(Room room, Hotel hotel) {
        Room existingRoom = roomRepository.findById(room.getNumber(), hotel);

        if (existingRoom != null) {
            return roomRepository.update(room);
        } else {
            System.out.println("Room not found.");
            return false;
        }
    }

}
