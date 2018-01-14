package kr.rvs.kkutu.game;

import kr.rvs.kkutu.gui.GameProcessor;
import kr.rvs.kkutu.gui.RoomController;

import java.net.URL;

public interface GameProcessorFactory {
    GameProcessor create(RoomController controller);

    URL fxmlUrl();
}
