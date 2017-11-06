package kr.rvs.kkutu.holder;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.gui.LobbyController;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class RoomHolder {
    private static final RoomHolder inst = new RoomHolder();
    private LobbyController controller;
    private final Map<Integer, Room> roomMap = new ConcurrentHashMap<>();

    public static RoomHolder getInst() {
        return inst;
    }

    private void refresh() {
        controller.updateRooms(roomMap);
    }

    public void add(Room room) {
        roomMap.put(room.getId(), room);
        refresh();
    }

    public void add(Map<Integer, Room> map) {
        roomMap.putAll(map);
        refresh();
    }

    public Room remove(Integer key) {
        Logger.getLogger()
        Room old = roomMap.remove(key);
        refresh();
        return old;
    }

    public Optional<Room> get(Integer key) {
        return Optional.ofNullable(roomMap.get(key));
    }

    public void setController(LobbyController controller) {
        this.controller = controller;
    }
}
