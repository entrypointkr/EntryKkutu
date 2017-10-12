package kr.rvs.kkutu.network.handler;

import com.google.inject.Inject;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.holder.RoomHolder;
import kr.rvs.kkutu.holder.UserHolder;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.in.ConnectPacket;
import kr.rvs.kkutu.network.packet.in.DisconnectPacket;
import kr.rvs.kkutu.network.packet.in.RoomPacket;
import kr.rvs.kkutu.network.packet.in.WelcomePacket;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class UpdateHandler implements PacketHandler {
    private final UserHolder userHolder;
    private final RoomHolder roomHolder;

    @Inject
    public UpdateHandler(UserHolder userHolder, RoomHolder roomHolder) {
        this.userHolder = userHolder;
        this.roomHolder = roomHolder;
    }

    @Override
    public void handle(Packet packet) {
        if (packet instanceof WelcomePacket) {
            WelcomePacket welcomePacket = ((WelcomePacket) packet);
            userHolder.add(welcomePacket.users);
            roomHolder.add(welcomePacket.rooms);
        } else if (packet instanceof ConnectPacket) {
            ConnectPacket connectPacket = ((ConnectPacket) packet);
            userHolder.add(connectPacket.user);
        } else if (packet instanceof DisconnectPacket) {
            DisconnectPacket disconnectPacket = ((DisconnectPacket) packet);
            userHolder.remove(disconnectPacket.id);
        } else if (packet instanceof RoomPacket) {
            RoomPacket roomPacket = ((RoomPacket) packet);
            Room room = roomPacket.room;
            if (!room.getPlayers().isEmpty()) {
                roomHolder.add(room);
            } else {
                roomHolder.remove(room.getId());
            }
        }
    }
}
