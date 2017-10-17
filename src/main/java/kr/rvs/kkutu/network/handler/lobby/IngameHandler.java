package kr.rvs.kkutu.network.handler.lobby;

import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.PacketHandlers;
import kr.rvs.kkutu.network.packet.PacketManager;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class IngameHandler implements PacketHandler {
    private final RoomController controller;
    private final PacketManager manager;

    public IngameHandler(RoomController controller, PacketManager manager) {
        this.controller = controller;
        this.manager = manager;
    }

    @Override
    public void handle(PacketHandlers handlers, Packet packet) {

    }

    public void exit() {

    }
}
