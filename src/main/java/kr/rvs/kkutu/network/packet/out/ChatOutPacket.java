package kr.rvs.kkutu.network.packet.out;

import com.google.gson.annotations.SerializedName;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketType;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class ChatOutPacket implements Packet {
    @SerializedName("value")
    private final String content;

    public ChatOutPacket(String content) {
        this.content = content;
    }

    @Override
    public PacketType type() {
        return PacketType.OUT_CHAT;
    }
}
