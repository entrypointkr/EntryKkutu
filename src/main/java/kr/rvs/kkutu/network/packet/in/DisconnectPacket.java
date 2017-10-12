package kr.rvs.kkutu.network.packet.in;

import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketType;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class DisconnectPacket implements Packet {
    public String id;

    @Override
    public PacketType type() {
        return PacketType.IN_DISCONNECT;
    }
}
