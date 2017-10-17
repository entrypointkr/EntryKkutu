package kr.rvs.kkutu.network.handler.lobby;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.netty.WebSocketClient;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.PacketHandlers;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.network.packet.impl.in.PreRoomPacket;
import kr.rvs.kkutu.network.packet.impl.out.RoomEnterPacket;
import kr.rvs.kkutu.util.Server;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class RoomHandler implements PacketHandler, EventHandler<MouseEvent> {
    private final Server server;
    private final LobbyController controller;
    private final PacketManager manager;

    public RoomHandler(Server server, LobbyController controller, PacketManager manager) {
        this.server = server;
        this.controller = controller;
        this.manager = manager;
    }

    @Override
    public void handle(PacketHandlers handlers, Packet packet) {
        packet.cast(PreRoomPacket.class).ifPresent(preRoom -> {
            PacketManager manager = new PacketManager(server.getServer());
            WebSocketClient client = new WebSocketClient(
                    "room",
                    server.getUri(preRoom.channel, preRoom.id),
                    manager
            );
            client.start();

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Room.fxml"));
                RoomController controller = new RoomController();
                IngameHandler ingameHandler = new IngameHandler(controller, manager);

                loader.setController(controller);
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle(preRoom.id + " 번 방");
                stage.setScene(new Scene(root));
                stage.show();
                stage.setOnCloseRequest(event -> manager.closeChannel());
                manager.getHandlers().add(ingameHandler);
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() % 2 == 0) {
            Room room = controller.getSelectedRoom();
            manager.send(new RoomEnterPacket(room.getId()));
        }
    }
}
