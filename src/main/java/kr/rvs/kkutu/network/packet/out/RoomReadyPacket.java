package kr.rvs.kkutu.network.packet.out;

import kr.rvs.kkutu.network.packet.Packet;

public class RoomReadyPacket implements Packet {
    @Override
    public String type() {
        return "ready";
    }
}
