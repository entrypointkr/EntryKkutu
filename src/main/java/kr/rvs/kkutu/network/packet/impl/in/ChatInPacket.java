package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.network.packet.ReadablePacket;
import kr.rvs.kkutu.util.Validate;

public class ChatInPacket implements ReadablePacket {
    private String value;
    private Profile profile;

    @Override
    public void read(JsonObject json) {
        Validate.isTrue(json.has("value") && json.has("profile"), "Illegal chat packet.");
        this.value = json.get("value").getAsString();
        this.profile = Profile.of(json.get("profile").getAsJsonObject());
    }

    @Override
    public String type() {
        return "chat";
    }

    public String getMessage() {
        return value;
    }

    public Profile getProfile() {
        return profile;
    }
}
