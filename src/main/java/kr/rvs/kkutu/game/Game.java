package kr.rvs.kkutu.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.util.Validate;

public class Game {
    private boolean ready = false;

    public static Game of(JsonObject json) {
        Validate.isTrue(json.has("ready"));
        return new Game(json.get("ready").getAsBoolean());
    }

    public Game(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
