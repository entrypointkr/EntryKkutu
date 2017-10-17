package kr.rvs.kkutu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.network.netty.WebSocketClient;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.util.Server;
import kr.rvs.kkutu.util.Servers;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class EntryKkutu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Server server = new Server(Servers.KKUTU_COKR, 0);
        PacketManager packetManager = new PacketManager(server.getServer());
        new WebSocketClient("lobby", server.getUri(), packetManager).start();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Lobby.fxml"));
        loader.setController(new LobbyController(server, packetManager));
        Parent root = loader.load();
        int minWidth = 1000;
        int minHeight = 550;

        primaryStage.setTitle("EntryKkutu");
        primaryStage.setScene(new Scene(root, minWidth, minHeight));
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.show();
    }
}
