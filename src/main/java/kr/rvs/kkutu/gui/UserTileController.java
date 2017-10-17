package kr.rvs.kkutu.gui;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.util.Server;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Junhyeong Lim on 2017-10-18.
 */
public class UserTileController implements Initializable {
    private final Server server;
    private final User user;
    public ImageView profileImage;
    public Text status;
    public Text name;

    public UserTileController(Server server, User user) {
        this.server = server;
        this.user = user;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        profileImage.setImage(user.getProfile().getImage(server));
        status.setText("방장");
        name.setText(user.getProfile().getName());
    }
}
