package kr.rvs.kkutu.gui;

import javafx.fxml.Initializable;
import javafx.scene.text.Text;
import kr.rvs.kkutu.game.room.RoomPlayer;

import java.net.URL;
import java.util.ResourceBundle;

public class GamePlayerTileController implements Initializable {
    private final RoomPlayer player;
    public Text nickText;
    public Text scoreText;

    public GamePlayerTileController(RoomPlayer player) {
        this.player = player;
    }

    public void refresh() {
        nickText.setText(player.getName());

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
    }
}
