package kr.rvs.kkutu.network;

import kr.rvs.kkutu.network.packet.Packet;

public interface PacketHandler {
    void handle(PacketManager manager, Packet packet);
}
