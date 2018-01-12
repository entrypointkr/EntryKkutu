package kr.rvs.kkutu;

import com.google.gson.JsonObject;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.LobbyPacketManager;
import kr.rvs.kkutu.network.handler.ErrorHandler;
import kr.rvs.kkutu.network.impl.KkutuKoreaPacketFactory;
import kr.rvs.kkutu.network.netty.WebSocket;
import kr.rvs.kkutu.util.Gsons;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;

public class EntryKkutu extends Application {
    private static final JsonObject lang;

    static {
        InputStream in = ErrorHandler.class.getResourceAsStream("/lang/ko_kr.json");
        lang = Gsons.getParser().parse(new InputStreamReader(in)).getAsJsonObject();
    }

    public static JsonObject getLang() {
        return lang;
    }

    public static void showRoom(Room room, EventHandler<WindowEvent> closeCallback) {
        EntryKkutu.runOnMain(() -> {
            FXMLLoader loader = new FXMLLoader(EntryKkutu.class.getResource("/Room.fxml"));
            RoomController controller = new RoomController(room);
            loader.setController(controller);
            room.setController(controller);

            int width = 1000;
            int height = 600;

            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle(room.toString() + " - EntryKkutu");
                stage.setScene(new Scene(root, width, height));
                stage.setMinWidth(width);
                stage.setMinHeight(height);
                stage.setOnCloseRequest(closeCallback);
                stage.show();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    public static void runOnMain(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    public static void showMessage(Alert.AlertType type, String message) {
        runOnMain(() -> new Alert(type, message).show());
    }

    @Override
    public void start(Stage stage) throws Exception {
        int width = 1000;
        int height = 550;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Lobby.fxml"));
        loader.setController(LobbyController.get());

        Parent root = loader.load();
        stage.setTitle("EntryKkutu");
        stage.setScene(new Scene(root, width, height));
        stage.setMinWidth(width);
        stage.setMinHeight(height);
        stage.setOnCloseRequest(e -> System.exit(0));
        stage.show();

        WebSocket client = new WebSocket(
                "Lobby",
                URI.create("wss://ws.kkutu.co.kr:8080/2c727562e48cc83922ee306e9af3ed957500ed12833a0d4e8c8a0127430d219ac015a9670ceb4905e4f5abe8a422dc56"),
                KkutuKoreaPacketFactory.get(),
                LobbyPacketManager.get()
        );
        client.start();

        LobbyController.get().setServerName("끄투코리아");
    }
}
