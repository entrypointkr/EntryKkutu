package kr.rvs.kkutu.game.holder;

import javafx.collections.ObservableList;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.util.Static;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class RoomHolder {
    private static final Map<String, Room> ROOM_MAP = new ConcurrentHashMap<>();

    private static void callback(Consumer<ObservableList<Room>> consumer) {
        Static.runOnMain(() -> consumer.accept(LobbyController.get().roomView.getItems()));
    }

    public static void put(Room room) {
        if (ROOM_MAP.containsKey(room.getId())) {
            ROOM_MAP.get(room.getId()).update(room);
        } else {
            ROOM_MAP.put(room.getId(), room);
            callback(items -> items.add(room));
        }
    }

    public static void remove(String id) {
        ROOM_MAP.remove(id);
        callback(items -> items.removeIf(room -> room.getId().equals(id)));
    }

    public static Optional<Room> get(String id) {
        return Optional.ofNullable(ROOM_MAP.get(id));
    }

    public static Room getOrThrow(String id) {
        return get(id).orElseThrow(IllegalStateException::new);
    }

    private RoomHolder() {
    }
}
