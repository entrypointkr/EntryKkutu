package kr.rvs.kkutu.gui;

import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gui.data.RoomItem;
import kr.rvs.kkutu.gui.listener.InstantTextClear;
import kr.rvs.kkutu.gui.listener.UserSearchListener;
import kr.rvs.kkutu.holder.RoomHolder;
import kr.rvs.kkutu.holder.UserHolder;
import kr.rvs.kkutu.network.handler.lobby.ChatHandler;
import kr.rvs.kkutu.network.handler.lobby.JoinHandler;
import kr.rvs.kkutu.network.handler.lobby.UpdateHandler;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.util.Environment;
import kr.rvs.kkutu.util.Server;

import java.net.URL;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class LobbyController implements Initializable, Chatable {
    private final Server server;
    private final PacketManager packetManager;
    public TextArea chatArea;
    public TextField chatField;
    public TableView<RoomItem> roomView;
    public ListView<String> userView;
    public TitledPane titledUsersPane;
    public TextField userSearchField;
    public Label userNameLabel;
    public Label totalWinLabel;
    public Label hasMoneyLabel;
    public ImageView profileImage;
    public ProgressBar feverBar;
    public Label userLevelLabel;
    public ProgressBar expBar;
    public HBox hBox;

    public LobbyController(Server server, PacketManager packetManager) {
        this.server = server;
        this.packetManager = packetManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Application
        Environment.setServer(server);
        UserHolder.getInst().setController(this);
        RoomHolder.getInst().setController(this);

        ChatHandler chatHandler = new ChatHandler(packetManager, this);
        JoinHandler joinHandler = new JoinHandler(server, this, packetManager);

        packetManager.getHandlers().add(
                chatHandler,
                new UpdateHandler(this),
                joinHandler
        );

        // JavaFX
        chatField.focusedProperty().addListener(new InstantTextClear(chatField));
        chatField.setOnKeyPressed(chatHandler);
        userSearchField.focusedProperty().addListener(new InstantTextClear(userSearchField));
        userSearchField.textProperty().addListener(new UserSearchListener(userView));
        roomView.setRowFactory(param -> {
            TableRow<RoomItem> row = new TableRow<RoomItem>() {
                @Override
                protected void updateItem(RoomItem item, boolean empty) {
                    super.updateItem(item, empty);
                    boolean ingame = !empty && item != null && !item.getRoom().isJoinable();
                    setDisable(ingame);
                    setStyle(ingame ? "-fx-background-color:lightgrey" : null);
                }
            };
            row.setOnMouseClicked(joinHandler);
            return row;
        });
    }

    @Override
    public void chat(String name, String message) {
        javafx.application.Platform.runLater(() -> {
            if (chatArea.getLength() > 0)
                chatArea.appendText("\n");
            chatArea.appendText(String.format("%s: %s", name, message));
        });
    }

    public void updateUsers(Map<String, User> userMap) {
        javafx.application.Platform.runLater(() -> {
            ObservableList<String> items = userView.getItems();
            Set<String> set = new HashSet<>(userMap.size());
            for (User user : userMap.values())
                set.add(user.getProfile().getName());
            set.removeAll(items);
            items.addAll(set);
            userView.refresh();
            titledUsersPane.setText(String.format("%s (%d명)", server.getServer().getName(), items.size()));
        });
    }

    public void updateRooms(Map<Integer, Room> roomMap) {
        javafx.application.Platform.runLater(() -> {
            ObservableList<RoomItem> items = roomView.getItems();
            Set<RoomItem> remain = new HashSet<>(roomMap.size());
            roomMap.values().forEach(room -> remain.add(new RoomItem(room)));
            Iterator<RoomItem> iterator = items.iterator();
            while (iterator.hasNext()) {
                RoomItem item = iterator.next();
                Room newRoom = roomMap.get(item.getRoom().getId());
                if (newRoom != null) {
                    item.setRoom(newRoom);
                } else {
                    iterator.remove();
                }
                remain.remove(item);
            }
            items.addAll(remain);
            items.sort((o1, o2) ->
                    Boolean.compare(o2.getRoom().isJoinable(), o1.getRoom().isJoinable()));
            roomView.refresh();
        });
    }

    public void myProfileInit(User me) {
        javafx.application.Platform.runLater(() -> {
            profileImage.setImage(me.getProfile().getImage());
            userNameLabel.setText(me.getProfile().getName());
            hasMoneyLabel.setText(me.getMoney() + " 핑");
            userLevelLabel.setText("1 레벨");
        });
    }

    public Room getSelectedRoom() {
        return roomView.getSelectionModel().getSelectedItem().getRoom();
    }
}
