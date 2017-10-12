package kr.rvs.kkutu.network.packet;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class EmptyPacket implements Packet {
    @Override
    public PacketType type() {
        return PacketType.EMPTY;
    }
}
