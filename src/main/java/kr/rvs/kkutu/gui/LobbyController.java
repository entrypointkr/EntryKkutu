package kr.rvs.kkutu.gui;

import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.LobbyPacketManager;
import kr.rvs.kkutu.network.handler.ChatHandler;
import kr.rvs.kkutu.network.handler.RoomJoinHandler;
import kr.rvs.kkutu.network.handler.UpdateHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class LobbyController implements Initializable, Chatable {
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

    @Override
    public void chat(String message) {
        chatArea.appendText(message);
        chatArea.appendText("\n");
    }

    @Override
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
        ChatHandler chatHandler = new ChatHandler(this, LobbyPacketManager.get());
        UpdateHandler updateHandler = new UpdateHandler();
        RoomJoinHandler roomHandler = new RoomJoinHandler();
        LobbyPacketManager.get().addHandler(
                chatHandler,
                updateHandler,
                roomHandler
        );
        chatField.setOnKeyPressed(chatHandler);
        roomView.setRowFactory(table -> new RoomTableRow(roomHandler));

        setupInstantText(chatField);
        setupInstantText(userSearchField);
    }
}
