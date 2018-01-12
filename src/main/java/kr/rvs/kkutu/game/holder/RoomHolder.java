package kr.rvs.kkutu.game.holder;

import javafx.collections.ObservableList;
import kr.rvs.kkutu.EntryKkutu;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.game.room.RoomData;
import kr.rvs.kkutu.gui.LobbyController;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

public class RoomHolder {
    private static final Map<String, RoomData> ROOM_MAP = new ConcurrentHashMap<>();

    private static void callback(Consumer<ObservableList<RoomData>> consumer) {
        EntryKkutu.runOnMain(() -> consumer.accept(LobbyController.get().roomView.getItems()));
    }

    public static void put(Room room) {
        if (ROOM_MAP.containsKey(room.getId())) {
            ROOM_MAP.get(room.getId()).getRoom().update(room);
        } else {
            RoomData data = new RoomData(room);
            ROOM_MAP.put(room.getId(), data);
            callback(items -> items.add(data));
        }
    }

    public static void remove(String id) {
        ROOM_MAP.remove(id);
        callback(items -> items.removeIf(room -> room.getId().equals(id)));
    }

    public static Optional<Room> get(String id) {
        return Optional.ofNullable(ROOM_MAP.get(id).getRoom());
    }

    public static Room getOrThrow(String id) {
        return get(id).orElseThrow(IllegalStateException::new);
    }

    private RoomHolder() {
    }
}
