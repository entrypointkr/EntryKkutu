package kr.rvs.kkutu.game;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import kr.rvs.kkutu.gui.LobbyController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class RoomHolder {
    private static final Map<String, Room> ROOM_MAP = new HashMap<>();

    private static void callback(Consumer<ObservableList<Room>> consumer) {
        Platform.runLater(() -> consumer.accept(LobbyController.get().roomView.getItems()));
    }

    public static void put(Room room) {
        if (ROOM_MAP.containsKey(room.getId())) {
            ROOM_MAP.get(room.getId()).update(room);
        } else {
            ROOM_MAP.put(room.getId(), room);
            callback(items -> items.add(room));
        }
    }

    public static void put(Map<String, Room> roomMap) {
        ROOM_MAP.putAll(roomMap);
        callback(items -> items.addAll(roomMap.values()));
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
