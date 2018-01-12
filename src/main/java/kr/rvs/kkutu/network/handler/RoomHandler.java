package kr.rvs.kkutu.network.handler;

import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.game.holder.UserHolder;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.game.room.RoomPlayer;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.RoomJoinPacket;
import kr.rvs.kkutu.network.packet.in.RoomPlayerUpdatePacket;
import kr.rvs.kkutu.network.packet.in.RoomQuitPacket;

public class RoomHandler implements PacketHandler {
    private final Room room;

    public RoomHandler(Room room) {
        this.room = room;
    }

    @Override
    public void handle(PacketManager manager, Packet packet) {
        if (packet instanceof RoomJoinPacket) {
            RoomJoinPacket joinPacket = (RoomJoinPacket) packet;
            User user = UserHolder.getOrThrow(joinPacket.getUser().getId());
            room.join(user);
        } else if (packet instanceof RoomQuitPacket) {
            RoomQuitPacket quitPacket = (RoomQuitPacket) packet;
            room.quit(quitPacket.getId());
        } else if (packet instanceof RoomPlayerUpdatePacket) {
            RoomPlayerUpdatePacket updatePacket = (RoomPlayerUpdatePacket) packet;
            RoomPlayer data = updatePacket.getPlayer();
            room.getPlayer(data.getId()).ifPresent(player -> {
                if (data.isReady() != player.isReady()) {
                    player.setReady(data.isReady());
                }
            });
        }
    }
}
