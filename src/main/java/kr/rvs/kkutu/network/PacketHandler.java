package kr.rvs.kkutu.network;

import kr.rvs.kkutu.network.packet.Packet;

public interface PacketHandler {
    void handle(Packet packet);
}
