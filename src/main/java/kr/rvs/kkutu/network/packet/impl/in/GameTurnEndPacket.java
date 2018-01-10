package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameTurnEndPacket implements ReadablePacket {
    private String mean;
    private boolean pass;
    private int score;
    private String word;

    @Override
    public String type() {
        return "turnEnd";
    }

    @Override
    public void read(JsonObject json) {
        this.mean = json.get("mean").getAsString();
        this.pass = json.get("ok").getAsBoolean();
        this.score = json.get("score").getAsInt();
        this.word = json.get("value").getAsString();
    }

    public String getMean() {
        return mean;
    }

    public boolean isPass() {
        return pass;
    }

    public int getScore() {
        return score;
    }

    public String getWord() {
        return word;
    }
}
