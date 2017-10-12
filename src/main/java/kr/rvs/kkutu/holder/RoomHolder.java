package kr.rvs.kkutu.holder;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.gui.data.RoomItem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class RoomHolder {
    private final TableView<RoomItem> roomView;
    private final Map<Integer, Room> roomMap = new ConcurrentHashMap<>();

    public RoomHolder(TableView<RoomItem> roomView) {
        this.roomView = roomView;
    }

    private void refresh() {
        ObservableList<RoomItem> items = roomView.getItems();

        Platform.runLater(() -> {
            // Calculating diff
            Map<Integer, Room> diffMap = new HashMap<>(roomMap);
            Iterator<RoomItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                RoomItem item = iterator.next();
                Room room = item.getRoom();
                if (room.getPlayers().isEmpty() || !diffMap.containsKey(room.getId())) {
                    iterator.remove();
                } else {
                    diffMap.remove(room.getId());
                }
            }

            // Add
            for (Room room : diffMap.values()) {
                items.add(new RoomItem(room));
            }
        });
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
}
