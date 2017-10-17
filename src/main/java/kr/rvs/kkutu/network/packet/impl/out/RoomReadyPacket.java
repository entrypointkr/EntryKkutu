package kr.rvs.kkutu.network.packet.impl.out;

import kr.rvs.kkutu.network.packet.Packet;

/**
 * Created by Junhyeong Lim on 2017-10-18.
 */
public class RoomReadyPacket implements Packet {
    @Override
    public String type() {
        return "ready";
    }
}
