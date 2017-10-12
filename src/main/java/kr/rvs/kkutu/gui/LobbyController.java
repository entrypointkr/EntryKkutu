package kr.rvs.kkutu.gui;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.gui.data.RoomItem;
import kr.rvs.kkutu.gui.listener.InstantTextClear;
import kr.rvs.kkutu.gui.listener.UserSearchListener;
import kr.rvs.kkutu.holder.RoomHolder;
import kr.rvs.kkutu.holder.UserHolder;
import kr.rvs.kkutu.network.PacketHandlers;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.WebSocketClient;
import kr.rvs.kkutu.network.handler.ChatHandler;
import kr.rvs.kkutu.network.handler.UpdateHandler;
import kr.rvs.kkutu.util.Servers;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class LobbyController implements Initializable {
    public TextArea chatArea;
    public TextField chatField;
    public TableView<RoomItem> roomView;
    public ListView<String> userView;
    public TitledPane titledUsersPane;
    public TextField userSearchField;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Servers server = Servers.KKUTU_IO;
        PacketManager manager = new PacketManager();
        UserHolder userHolder = new UserHolder(server, titledUsersPane, userView);
        RoomHolder roomHolder = new RoomHolder(roomView);

        Injector injector = Guice.createInjector((Module) binder -> {
            binder.bind(LobbyController.class).toInstance(this);
            binder.bind(PacketManager.class).toInstance(manager);
            binder.bind(UserHolder.class).toInstance(userHolder);
            binder.bind(RoomHolder.class).toInstance(roomHolder);
        });
        ChatHandler chatHandler = injector.getInstance(ChatHandler.class);
        UpdateHandler updateHandler = injector.getInstance(UpdateHandler.class);

        PacketHandlers handlers = manager.getHandlers();
        handlers.add(chatHandler, updateHandler);
        new WebSocketClient(server, manager).start();

        chatField.focusedProperty().addListener(new InstantTextClear(chatField));
        userSearchField.focusedProperty().addListener(new InstantTextClear(userSearchField));
        userSearchField.textProperty().addListener(new UserSearchListener(userView, userHolder));
    }

    public void chat(Profile profile, String message) {
        if (chatArea.getLength() > 0)
            chatArea.appendText("\n");
        chatArea.appendText(String.format("%s: %s", profile.getName(), message));
    }
}
