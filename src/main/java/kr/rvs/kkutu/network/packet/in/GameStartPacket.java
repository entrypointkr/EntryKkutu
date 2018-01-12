package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameStartPacket implements ReadablePacket {
    private String roomId;

    @Override
    public String type() {
        return "starting";
    }

    @Override
    public void read(JsonObject json) {
        this.roomId = json.get("target").getAsString();
    }

    public String getRoomId() {
        return roomId;
    }
}
