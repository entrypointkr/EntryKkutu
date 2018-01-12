package kr.rvs.kkutu.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.util.Validate;

public class Profile implements IdentityProvider {
    private final String id;
    private final String nick;

    public static Profile of(JsonObject json) {
        Validate.isTrue(json.has("id") && json.has("nick"), "Illegal profile format.");
        return new Profile(
                json.get("id").getAsString(),
                json.get("nick").getAsString()
        );
    }

    public Profile(String id, String nick) {
        this.id = id;
        this.nick = nick;
    }

    @Override
    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }
}
