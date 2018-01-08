package kr.rvs.kkutu.network.packet.impl;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.packet.ReadablePacket;
import kr.rvs.kkutu.util.Validate;

import java.util.HashMap;
import java.util.Map;

public class WelcomePacket implements ReadablePacket {
    private String id;
    private final Map<String, User> userMap = new HashMap<>();
    private final Map<String, Room> roomMap = new HashMap<>();

    @Override
    public String type() {
        return "welcome";
    }

    @Override
    public void read(JsonObject json) {
        Validate.isTrue(json.has("id")
                && json.has("users") && json.has("rooms"));
        this.id = json.get("id").getAsString();
        JsonObject users = json.get("users").getAsJsonObject();
        users.entrySet().forEach(entry -> userMap.put(entry.getKey(), User.of(entry.getValue().getAsJsonObject())));
        JsonObject rooms = json.get("rooms").getAsJsonObject();
        rooms.entrySet().forEach(entry -> roomMap.put(entry.getKey(), Room.of(entry.getValue().getAsJsonObject())));
    }

    public String getId() {
        return id;
    }

    public Map<String, User> getUserMap() {
        return userMap;
    }

    public Map<String, Room> getRoomMap() {
        return roomMap;
    }
}
