package kr.rvs.kkutu.holder;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.gui.LobbyController;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class RoomHolder {
    private final LobbyController controller;
    private final Map<Integer, Room> roomMap = new ConcurrentHashMap<>();

    public RoomHolder(LobbyController controller) {
        this.controller = controller;
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
        Room old = roomMap.remove(key);
        refresh();
        return old;
    }

    public Optional<Room> get(Integer key) {
        return Optional.ofNullable(roomMap.get(key));
    }
}
