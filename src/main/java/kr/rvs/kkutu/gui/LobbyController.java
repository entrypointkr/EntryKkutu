package kr.rvs.kkutu.gui;

import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.handler.ChatHandler;
import kr.rvs.kkutu.network.handler.UpdateHandler;

import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.ResourceBundle;

public class LobbyController implements Initializable {
    private static final LobbyController instance = new LobbyController();

    public TitledPane titledUsersPane;
    public ListView<User> userView;
    public TextField userSearchField;

    public TableView<Room> roomView;
    public TextArea chatArea;
    public TextField chatField;

    public ImageView profileImage;
    public Label userNameLabel;
    public Label userLevelLabel;
    public Label hasMoneyLabel;
    public Label totalWinLabel;

    public static LobbyController get() {
        return instance;
    }

    public void setServerName(String name) {
        titledUsersPane.setText(name);
    }

    public void chat(String message) {
        chatArea.appendText(message);
        chatArea.appendText("\n");
    }

    public void chat(Profile profile, String message) {
        chat(String.format("%s: %s", profile.getNick(), message));
    }

    public void myProfileInit(User user) {
        userNameLabel.setText(user.getProfile().getNick());
        userLevelLabel.setText("1 레벨");
        hasMoneyLabel.setText(user.getMoney() + " 핑");
        totalWinLabel.setText("통산 0 승");
    }

    private void setupInstantText(TextField field) {
        field.focusedProperty().addListener(new InstantTextCleaner(field));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ChatHandler chatHandler = new ChatHandler();
        UpdateHandler updateHandler = new UpdateHandler();
        PacketManager.get().addHandler(
                chatHandler,
                updateHandler
        );
        chatField.setOnKeyPressed(chatHandler);

        setupInstantText(chatField);
        setupInstantText(userSearchField);
    }
}
