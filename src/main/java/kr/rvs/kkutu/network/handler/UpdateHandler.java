package kr.rvs.kkutu.network.handler;

import kr.rvs.kkutu.EntryKkutu;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.game.holder.RoomHolder;
import kr.rvs.kkutu.game.holder.UserHolder;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.ConnectPacket;
import kr.rvs.kkutu.network.packet.in.DisconnectPacket;
import kr.rvs.kkutu.network.packet.in.RoomPacket;
import kr.rvs.kkutu.network.packet.in.WelcomePacket;
import kr.rvs.kkutu.util.Static;

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
            if (room.isEmpty()) {
                RoomHolder.remove(room.getId());
            } else {
                RoomHolder.put(room);
            }
        } else if (packet instanceof WelcomePacket) {
            WelcomePacket welcomePacket = ((WelcomePacket) packet);
            welcomePacket.getUsers().entrySet().forEach(entry ->
                    UserHolder.join(User.of(entry.getValue().getAsJsonObject())));
            welcomePacket.getRooms().entrySet().forEach(entry ->
                    RoomHolder.put(Room.of(entry.getValue().getAsJsonObject())));
            EntryKkutu.runOnMain(() -> LobbyController.get().myProfileInit(
                    UserHolder.getOrThrow(welcomePacket.getId())));
        }
    }
}
