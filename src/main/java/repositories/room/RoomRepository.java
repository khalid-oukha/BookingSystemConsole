package repositories.room;

import Entities.Hotel;
import Entities.Room;

import java.util.List;

public interface RoomRepository {
    void create(Room room,Hotel hotel);
    Room findById(int id);
    List<Room> findAll(Hotel hotel);
    void update(Room room);
    void delete(Room room);
}
