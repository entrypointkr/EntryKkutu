package kr.rvs.kkutu.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.util.Validate;

public class UserGame {
    private boolean ready = false;

    public static UserGame of(JsonObject json) {
        Validate.isTrue(json.has("ready"));
        return new UserGame(json.get("ready").getAsBoolean());
    }

    public UserGame(boolean ready) {
        this.ready = ready;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }
}
