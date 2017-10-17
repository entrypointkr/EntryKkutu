package kr.rvs.kkutu.gui.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import kr.rvs.kkutu.game.GameType;
import kr.rvs.kkutu.game.Room;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class RoomItem {
    private Room room;

    public RoomItem(Room room) {
        this.room = room;
    }

    public SimpleIntegerProperty idProperty() {
        return new SimpleIntegerProperty(room.getId());
    }

    public SimpleStringProperty titleProperty() {
        return new SimpleStringProperty(room.getTitle());
    }

    public SimpleStringProperty modeProperty() {
        GameType type = room.getMode();
        if (type == null) {
            System.out.println("a");
        }
        return new SimpleStringProperty(room.getMode().getName());
    }

    public SimpleIntegerProperty roundProperty() {
        return new SimpleIntegerProperty(room.getRound());
    }

    public SimpleStringProperty timeProperty() {
        return new SimpleStringProperty(room.getTime() + "초");
    }

    public SimpleStringProperty sizeProperty() {
        return new SimpleStringProperty(room.getTotalPlayersStr());
    }

    public SimpleStringProperty passwordProperty() {
        return new SimpleStringProperty(room.isPrivate() ? "예" : "아니오");
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomItem roomItem = (RoomItem) o;

        return room != null ? room.equals(roomItem.room) : roomItem.room == null;
    }

    @Override
    public int hashCode() {
        return room != null ? room.hashCode() : 0;
    }
}
