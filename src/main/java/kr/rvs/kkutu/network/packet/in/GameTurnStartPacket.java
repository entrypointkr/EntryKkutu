package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameTurnStartPacket implements ReadablePacket {
    private char startChar;
    private int roundTime;
    private int turnTime;

    @Override
    public String type() {
        return "turnStart";
    }

    @Override
    public void read(JsonObject json) {
        this.startChar = json.get("char").getAsCharacter();
        this.roundTime = json.get("roundTime").getAsInt();
        this.turnTime = json.get("turnTime").getAsInt();
    }

    public char getStartChar() {
        return startChar;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public int getTurnTime() {
        return turnTime;
    }
}
