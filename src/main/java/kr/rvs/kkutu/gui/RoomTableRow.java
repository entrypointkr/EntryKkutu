package kr.rvs.kkutu.gui;

import javafx.scene.control.TableRow;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.network.handler.RoomJoinHandler;

public class RoomTableRow extends TableRow<Room> {
    public RoomTableRow(RoomJoinHandler handler) {
        setOnMouseClicked(handler);
    }

    @Override
    protected void updateItem(Room item, boolean empty) {
        super.updateItem(item, empty);
        boolean ingame = !empty && item != null && !item.isJoinable();
        setDisable(ingame);
        setStyle(ingame ? "-fx-background-color:lightgrey" : null);
    }
}
