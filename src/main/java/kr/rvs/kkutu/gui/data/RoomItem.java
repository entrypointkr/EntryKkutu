package kr.rvs.kkutu.gui.data;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import kr.rvs.kkutu.game.Room;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class RoomItem {
    private final Room room;

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
        return new SimpleStringProperty(room.getMode().getName());
    }

    public SimpleIntegerProperty roundProperty() {
        return new SimpleIntegerProperty(room.getRound());
    }

    public SimpleStringProperty timeProperty() {
        return new SimpleStringProperty(room.getTime() + "초");
    }

    public SimpleStringProperty sizeProperty() {
        return new SimpleStringProperty(room.getPlayers().size() + "/" + room.getLimit());
    }

    public SimpleStringProperty passwordProperty() {
        return new SimpleStringProperty(room.isPrivate() ? "예" : "아니오");
    }

    public Room getRoom() {
        return room;
    }
}
