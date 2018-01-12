package kr.rvs.kkutu.gui;

import javafx.scene.control.TableRow;
import kr.rvs.kkutu.game.room.RoomData;
import kr.rvs.kkutu.network.handler.RoomJoinHandler;

public class RoomTableRow extends TableRow<RoomData> {
    public RoomTableRow(RoomJoinHandler handler) {
        setOnMouseClicked(handler);
    }

    @Override
    protected void updateItem(RoomData item, boolean empty) {
        super.updateItem(item, empty);
        boolean ingame = !empty && !item.getRoom().isJoinable();
        setDisable(ingame);
        setStyle(ingame ? "-fx-background-color:lightgrey" : null);
    }
}
