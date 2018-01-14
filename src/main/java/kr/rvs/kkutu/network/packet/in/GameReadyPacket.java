package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameReadyPacket implements ReadablePacket {
    private char startChar;
    private Character subChar;
    private int round;

    @Override
    public String type() {
        return "roundReady";
    }

    @Override
    public void read(JsonObject json) {
        this.startChar = json.get("char").getAsCharacter();
        if (json.has("subChar")) {
            this.subChar = json.get("subChar").getAsCharacter();
        }
        this.round = json.get("round").getAsInt();
    }

    public char getStartChar() {
        return startChar;
    }

    public Character getSubChar() {
        return subChar;
    }

    public int getRound() {
        return round;
    }
}
