package kr.rvs.kkutu.network.handler;

import javafx.application.Platform;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.RoomHolder;
import kr.rvs.kkutu.game.UserHolder;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.impl.ConnectPacket;
import kr.rvs.kkutu.network.packet.impl.DisconnectPacket;
import kr.rvs.kkutu.network.packet.impl.RoomPacket;
import kr.rvs.kkutu.network.packet.impl.WelcomePacket;

public class UpdateHandler implements PacketHandler {
    @Override
    public void handle(Packet packet) {
        if (packet instanceof ConnectPacket) {
            ConnectPacket connectPacket = ((ConnectPacket) packet);
            UserHolder.join(connectPacket.getUser());
        } else if (packet instanceof DisconnectPacket) {
            DisconnectPacket disconnectPacket = ((DisconnectPacket) packet);
            UserHolder.quit(disconnectPacket.getId());
        } else if (packet instanceof RoomPacket) {
            RoomPacket roomPacket = ((RoomPacket) packet);
            Room room = roomPacket.getRoom();
            if (room.getPlayers().isEmpty()) {
                RoomHolder.remove(room.getId());
            } else {
                RoomHolder.put(room);
            }
        } else if (packet instanceof WelcomePacket) {
            WelcomePacket welcomePacket = ((WelcomePacket) packet);
            UserHolder.join(welcomePacket.getUserMap());
            RoomHolder.put(welcomePacket.getRoomMap());
            Platform.runLater(() -> LobbyController.get().myProfileInit(
                    UserHolder.getOrThrow(welcomePacket.getId())));
        }
    }
}
