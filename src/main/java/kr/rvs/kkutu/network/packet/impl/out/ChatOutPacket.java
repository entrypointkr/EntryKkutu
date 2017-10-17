package kr.rvs.kkutu.network.packet.impl.out;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.WritablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class ChatOutPacket implements WritablePacket {
    private final String content;

    public ChatOutPacket(String content) {
        this.content = content;
    }

    @Override
    public String type() {
        return "talk";
    }

    @Override
    public void write(JsonObject json) {
        json.addProperty("value", content);
    }
}
