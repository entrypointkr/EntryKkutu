package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameTurnErrorPacket implements ReadablePacket {
    private int code;
    private String word;
    private Profile profile;

    @Override
    public String type() {
        return "turnError";
    }

    @Override
    public void read(JsonObject json) {
        this.code = json.get("code").getAsInt();
        this.word = json.get("value").getAsString();
        this.profile = Profile.of(json.get("profile").getAsJsonObject());
    }

    public int getCode() {
        return code;
    }

    public String getWord() {
        return word;
    }

    public Profile getProfile() {
        return profile;
    }
}
