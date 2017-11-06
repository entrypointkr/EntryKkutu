package kr.rvs.kkutu.network.handler.lobby;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.holder.RoomHolder;
import kr.rvs.kkutu.holder.UserHolder;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.PacketHandlers;
import kr.rvs.kkutu.network.packet.impl.in.ConnectPacket;
import kr.rvs.kkutu.network.packet.impl.in.DisconnectPacket;
import kr.rvs.kkutu.network.packet.impl.in.RoomPacket;
import kr.rvs.kkutu.network.packet.impl.in.WelcomePacket;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class UpdateHandler implements PacketHandler {
    private final LobbyController controller;

    public UpdateHandler(LobbyController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(PacketHandlers handlers, Packet packet) {
        UserHolder userHolder = UserHolder.getInst();
        RoomHolder roomHolder = RoomHolder.getInst();
        if (packet instanceof WelcomePacket) {
            WelcomePacket welcomePacket = ((WelcomePacket) packet);
            userHolder.add(welcomePacket.userMap);
            roomHolder.add(welcomePacket.roomMap);
            userHolder.get(welcomePacket.id).ifPresent(controller::myProfileInit);
        } else if (packet instanceof ConnectPacket) {
            ConnectPacket connectPacket = ((ConnectPacket) packet);
            userHolder.add(connectPacket.user);
        } else if (packet instanceof DisconnectPacket) {
            DisconnectPacket disconnectPacket = ((DisconnectPacket) packet);
            userHolder.remove(disconnectPacket.id);
        } else if (packet instanceof RoomPacket) {
            RoomPacket roomPacket = ((RoomPacket) packet);
            Room room = roomPacket.room;
            if (!room.getPlayerMap().isEmpty()) {
                roomHolder.add(room);
            } else {
                roomHolder.remove(room.getId());
            }
        }
    }
}
