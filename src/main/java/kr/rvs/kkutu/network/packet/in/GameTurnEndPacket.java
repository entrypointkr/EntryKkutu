package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameTurnEndPacket implements ReadablePacket {
    private boolean pass;
    private int score;
    private String word;
    private String mean;
    private String hint;

    @Override
    public String type() {
        return "turnEnd";
    }

    @Override
    public void read(JsonObject json) {
        this.pass = json.get("ok").getAsBoolean();
        this.score = json.get("score").getAsInt();
        if (json.has("value")) {
            this.word = json.get("value").getAsString();
        }
        if (json.has("mean")) {
            this.mean = json.get("mean").getAsString();
        }
        if (json.has("hint")) {
            this.hint = json.get("hint").getAsJsonObject().get("_id").getAsString();
        }
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

    public String getMean() {
        return mean;
    }

    public String getHint() {
        return hint;
    }

    public String getWordAnyway() {
        return isPass() ? getWord() : getHint();
    }
}
