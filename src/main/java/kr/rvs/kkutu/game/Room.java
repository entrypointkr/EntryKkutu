package kr.rvs.kkutu.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.util.Gsons;

import java.util.List;
import java.util.Objects;

public class Room {
    private final String id;
    private final String channel;
    private String title;
    private boolean password;
    private int limit;
    private int mode;
    private int round;
    private int time;
    private String master;
    private List players;
    private boolean ingame;

    public static Room of(JsonObject json) {
        return new Room(
                json.get("id").getAsString(),
                json.get("channel").getAsString(),
                json.get("title").getAsString(),
                json.get("password").getAsBoolean(),
                json.get("limit").getAsInt(),
                json.get("mode").getAsInt(),
                json.get("round").getAsInt(),
                json.get("time").getAsInt(),
                json.has("master") ? json.get("master").getAsString() : "null",
                Gsons.remapJsonArray(json.get("players").getAsJsonArray(), element -> element.isJsonPrimitive() ? element.getAsString() : "bot"),
                json.get("gaming").getAsBoolean()
        );
    }

    public Room(String id, String channel, String title, boolean password, int limit, int mode, int round, int time, String master, List players, boolean ingame) {
        this.id = id;
        this.channel = channel;
        this.title = title;
        this.password = password;
        this.limit = limit;
        this.mode = mode;
        this.round = round;
        this.time = time;
        this.master = master;
        this.players = players;
        this.ingame = ingame;
    }

    public void update(Room room) {
        this.title = room.title;
        this.password = room.password;
        this.limit = room.limit;
        this.mode = room.mode;
        this.round = room.round;
        this.time = room.time;
        this.master = room.master;
        this.players = room.players;
        this.ingame = room.ingame;
    }

    public String getId() {
        return id;
    }

    public String getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPassword() {
        return password;
    }

    public int getLimit() {
        return limit;
    }

    public int getMode() {
        return mode;
    }

    public int getRound() {
        return round;
    }

    public int getTime() {
        return time;
    }

    public String getMaster() {
        return master;
    }

    public List getPlayers() {
        return players;
    }

    public boolean isIngame() {
        return ingame;
    }

    @Override
    public String toString() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(id, room.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
