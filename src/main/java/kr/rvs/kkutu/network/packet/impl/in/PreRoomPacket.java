package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;
import kr.rvs.kkutu.util.Validate;

public class PreRoomPacket implements ReadablePacket {
    private String id;
    private int channel;

    @Override
    public String type() {
        return "preRoom";
    }

    @Override
    public void read(JsonObject json) {
        Validate.isTrue(json.has("id") && json.has("channel"));
        this.id = json.get("id").getAsString();
        this.channel = json.get("channel").getAsInt();
    }

    public String getId() {
        return id;
    }

    public int getChannel() {
        return channel;
    }
}
