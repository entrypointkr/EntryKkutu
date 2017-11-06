package kr.rvs.kkutu.gui;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.handler.lobby.ChatHandler;
import kr.rvs.kkutu.network.handler.room.RoomHandler;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.util.Server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class RoomController implements Initializable, Chatable {
    private final Server server;
    private final PacketManager packetManager;
    public TilePane userTilePane;
    public TextArea chatArea;
    public TextField chatField;

    public RoomController(Server server, PacketManager packetManager) {
        this.server = server;
        this.packetManager = packetManager;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        RoomHandler roomHandler = new RoomHandler(this, packetManager);
        ChatHandler chatHandler = new ChatHandler(packetManager, this);
        packetManager.getHandlers().add(
                roomHandler,
                chatHandler
        );
        chatField.setOnKeyPressed(chatHandler);
    }

    @Override
    public void chat(String name, String content) {
        Platform.runLater(() -> {
            if (chatArea.getLength() > 0)
                chatArea.appendText("\n");
            chatArea.appendText(String.format("%s: %s", name, content));
        });
    }

    public void addUser(User user) {
        Platform.runLater(() -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/part/RoomUserTile.fxml"));
                loader.setController(new UserTileController(server, user));
                VBox tile = loader.load();
                userTilePane.getChildren().add(tile);
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public void removeUser(String id) {

    }
}
