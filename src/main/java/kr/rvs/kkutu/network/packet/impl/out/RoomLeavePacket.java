package kr.rvs.kkutu.network.packet.impl.out;

import kr.rvs.kkutu.network.packet.Packet;

public class RoomLeavePacket implements Packet {
    @Override
    public String type() {
        return "leave";
    }
}
