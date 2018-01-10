package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class RoomQuitPacket implements ReadablePacket {
    private String id;

    @Override
    public String type() {
        return "disconnRoom";
    }

    @Override
    public void read(JsonObject json) {
        this.id = json.get("id").getAsString();
    }

    public String getId() {
        return id;
    }
}
