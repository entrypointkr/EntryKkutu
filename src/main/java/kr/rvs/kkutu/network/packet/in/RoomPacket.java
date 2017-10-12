package kr.rvs.kkutu.network.packet.in;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketType;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class RoomPacket implements Packet {
    public Room room;
    public String target;
    public String type;

    @Override
    public PacketType type() {
        return PacketType.IN_ROOM;
    }
}
