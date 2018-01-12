package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameTurnErrorPacket implements ReadablePacket {
    private int code;
    private String word;

    @Override
    public String type() {
        return "turnError";
    }

    @Override
    public void read(JsonObject json) {
        this.code = json.get("code").getAsInt();
        this.word = json.get("value").getAsString();
    }

    public int getCode() {
        return code;
    }

    public String getWord() {
        return word;
    }
}
