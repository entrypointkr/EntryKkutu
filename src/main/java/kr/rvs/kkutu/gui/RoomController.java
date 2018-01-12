package kr.rvs.kkutu.gui;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import kr.rvs.kkutu.EntryKkutu;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.game.room.RoomPlayer;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.handler.ChatHandler;
import kr.rvs.kkutu.network.handler.ErrorHandler;
import kr.rvs.kkutu.network.handler.RoomHandler;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RoomController implements Initializable, Chatable {
    private final Room room;
    public Button readyButton;
    public TilePane userTilePane;
    public TextArea chatArea;
    public TextField chatField;

    public RoomController(Room room) {
        this.room = room;
    }

    @Override
    public void chat(Profile profile, String message) {
        chat(String.format("%s: %s", profile.getNick(), message));
    }

    @Override
    public void chat(String message) {
        chatArea.appendText(message);
        chatArea.appendText("\n");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PacketManager manager = room.getPacketManager();
        ChatHandler chatHandler = new ChatHandler(this, manager);

        manager.addHandler(
                chatHandler,
                new RoomHandler(room),
                ErrorHandler.get()
        );
        chatField.setOnKeyPressed(chatHandler);
        chatField.focusedProperty().addListener(new InstantTextCleaner(chatField));

        readyButton.setOnMouseClicked(e -> room.ready());
        room.roomInit(this);
    }

    public void join(RoomPlayer player) {
        EntryKkutu.runOnMain(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/part/RoomUserTile.fxml"));
                RoomPlayerTileController tile = new RoomPlayerTileController(player, room);
                loader.setController(tile);
                player.setTileController(tile);
                Node node = loader.load();
                node.getProperties().put("id", player.getId());
                userTilePane.getChildren().add(node);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void quit(String id) {
        EntryKkutu.runOnMain(() -> userTilePane.getChildren().removeIf(node ->
                id.equals(node.getProperties().get("id"))));
    }
}
