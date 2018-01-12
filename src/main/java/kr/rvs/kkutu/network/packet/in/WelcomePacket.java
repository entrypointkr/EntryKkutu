package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;
import kr.rvs.kkutu.util.Validate;

public class WelcomePacket implements ReadablePacket {
    private String id;
    private JsonObject users;
    private JsonObject rooms;

    @Override
    public String type() {
        return "welcome";
    }

    @Override
    public void read(JsonObject json) {
        Validate.isTrue(json.has("id")
                && json.has("users") && json.has("rooms"));
        this.id = json.get("id").getAsString();
        this.users = json.get("users").getAsJsonObject();
        this.rooms = json.get("rooms").getAsJsonObject();
    }

    public String getId() {
        return id;
    }

    public JsonObject getUsers() {
        return users;
    }

    public JsonObject getRooms() {
        return rooms;
    }
}
