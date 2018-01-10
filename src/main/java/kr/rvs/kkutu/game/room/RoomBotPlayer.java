package kr.rvs.kkutu.game.room;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.util.Validate;

public class RoomBotPlayer extends RoomPlayer {
    private final String id;
    private final int level;

    public static RoomBotPlayer of(JsonObject json) {
        Validate.isTrue(json.has("id") && json.has("level"), json.toString());
        return new RoomBotPlayer(
                json.get("id").getAsString(),
                json.get("level").getAsInt()
        );
    }

    public RoomBotPlayer(String id, int level) {
        this.id = id;
        this.level = level;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getName() {
        return level + " 레벨 봇";
    }

    @Override
    public boolean isReady() {
        return true;
    }

    @Override
    protected void setReady0(boolean ready) {
        // Ignore
    }
}
