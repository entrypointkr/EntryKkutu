package kr.rvs.kkutu.gui;

import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.game.room.RoomPlayer;
import kr.rvs.kkutu.game.room.RoomPlayerStatus;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomPlayerTileController implements Initializable {
    private final RoomPlayer user;
    private final Room room;
    public ImageView profileImage;
    public Text name;
    public Text status;

    public RoomPlayerTileController(RoomPlayer user, Room room) {
        this.user = user;
        this.room = room;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.name.setText(user.getName());
        refreshStatus();
    }

    public void refreshStatus() {
        RoomPlayerStatus.getStatus(room, user).textEffect(status);
    }
}
