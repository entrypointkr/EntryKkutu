package kr.rvs.kkutu.gui;

import javafx.fxml.Initializable;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.network.PacketHandler;

public interface GameProcessor extends Initializable, PacketHandler {
    void update(Room room);
}
