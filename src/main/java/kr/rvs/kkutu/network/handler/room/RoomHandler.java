package kr.rvs.kkutu.network.handler.room;

import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.PacketHandlers;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.network.packet.impl.in.RoomJoinPacket;
import kr.rvs.kkutu.network.packet.impl.in.RoomQuitPacket;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class RoomHandler implements PacketHandler {
    private final RoomController controller;
    private final PacketManager manager;

    public RoomHandler(RoomController controller, PacketManager manager) {
        this.controller = controller;
        this.manager = manager;
    }

    @Override
    public void handle(PacketHandlers handlers, Packet packet) {
        packet.cast(RoomJoinPacket.class).ifPresent(joinPacket ->
                controller.addUser(joinPacket.user));
        packet.cast(RoomQuitPacket.class).ifPresent(quitPacket ->
                controller.removeUser(quitPacket.id));
    }
}
