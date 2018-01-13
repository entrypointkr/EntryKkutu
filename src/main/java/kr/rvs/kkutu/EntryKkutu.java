package kr.rvs.kkutu;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import kr.rvs.kkutu.game.IdentityProvider;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.LobbyPacketManager;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.handler.ErrorHandler;
import kr.rvs.kkutu.network.impl.KkutuKoreaPacketFactory;
import kr.rvs.kkutu.network.netty.WebSocket;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

public class EntryKkutu extends Application {
    private static final String ADDRESS = "https://kkutu.co.kr/?server=0";
    private static final URI WS_ADDRESS = getAddressFromWeb();
    private static final JsonObject LANG = readLang();
    private static Profile myProfile;

    private static JsonObject readLang() {
        InputStream in = ErrorHandler.class.getResourceAsStream("/lang/ko_kr.json");
        return new JsonParser().parse(new InputStreamReader(in)).getAsJsonObject();
    }

    private static URI getAddressFromWeb() {
        try {
            URL url = new URL(ADDRESS);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; ko) EntryKKuTu");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Language", "ko-KR");
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                String tag = "<span id=\"URL\">";
                String parse = response.substring(response.indexOf(tag));

                return URI.create(parse.substring(tag.length(), parse.indexOf("</span>")));
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public static JsonObject getLang() {
        return LANG;
    }

    public static void initMyProfile(Profile profile) {
        myProfile = profile;
    }

    public static Profile getMyProfile() {
        return myProfile;
    }

    public static boolean isMe(IdentityProvider provider) {
        return provider.getId().equals(myProfile.getId());
    }

    public static void initRoom(int channel, Room room) {
        PacketManager packetManager = new PacketManager();
        room.setPacketManager(packetManager);

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
                stage.setOnCloseRequest(e -> packetManager.close());
                stage.show();

                new WebSocket(
                        "Room",
                        new URI(
                                WS_ADDRESS.getScheme(),
                                WS_ADDRESS.getUserInfo(),
                                WS_ADDRESS.getHost(),
                                8495 + channel,
                                WS_ADDRESS.getPath() + String.format("&%s&%s", channel, room.getId()),
                                WS_ADDRESS.getQuery(),
                                WS_ADDRESS.getFragment()
                        ),
                        KkutuKoreaPacketFactory.get(),
                        packetManager,
                        () -> EntryKkutu.runOnMain(stage::close),
                        true
                ).start();
            } catch (Exception e) {
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
                WS_ADDRESS,
                KkutuKoreaPacketFactory.get(),
                LobbyPacketManager.get()
        );
        client.start();

        LobbyController.get().setServerName("끄투코리아");
    }
}
