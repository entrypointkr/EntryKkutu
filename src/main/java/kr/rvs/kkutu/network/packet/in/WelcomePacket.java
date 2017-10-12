package kr.rvs.kkutu.network.packet.in;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class WelcomePacket implements Packet {
    public String id;
    public boolean guest;
    public int playTime;
    public final Map<String, User> users = new HashMap<>();
    public final Map<Integer, Room> rooms = new HashMap<>();

    @Override
    public PacketType type() {
        return PacketType.IN_WELCOME;
    }
}
