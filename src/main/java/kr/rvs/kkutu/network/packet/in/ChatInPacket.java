package kr.rvs.kkutu.network.packet.in;

import com.google.gson.annotations.SerializedName;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketType;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class ChatInPacket implements Packet {
    @SerializedName("value")
    public String contents;
    @SerializedName("profile")
    public Profile author;

    @Override
    public PacketType type() {
        return PacketType.IN_CHAT;
    }
}
