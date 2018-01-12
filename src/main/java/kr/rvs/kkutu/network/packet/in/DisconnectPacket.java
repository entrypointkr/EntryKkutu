package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class DisconnectPacket implements ReadablePacket {
    private String id;

    @Override
    public String type() {
        return "disconn";
    }

    @Override
    public void read(JsonObject json) {
        this.id = json.get("id").getAsString();
    }

    public String getId() {
        return id;
    }
}
