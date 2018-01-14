package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameTurnStartPacket implements ReadablePacket {
    private char startChar;
    private Character subChar;
    private int roundTime;
    private int turnTime;
    private int turn;

    @Override
    public String type() {
        return "turnStart";
    }

    @Override
    public void read(JsonObject json) {
        this.startChar = json.get("char").getAsCharacter();
        if (json.has("subChar")) {
            this.subChar = json.get("subChar").getAsCharacter();
        }
        this.roundTime = json.get("roundTime").getAsInt();
        this.turnTime = json.get("turnTime").getAsInt();
        this.turn = json.get("turn").getAsInt();
    }

    public char getStartChar() {
        return startChar;
    }

    public Character getSubChar() {
        return subChar;
    }

    public int getRoundTime() {
        return roundTime;
    }

    public int getTurnTime() {
        return turnTime;
    }

    public int getTurn() {
        return turn;
    }
}
