package kr.rvs.kkutu.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.room.RoomPlayer;
import kr.rvs.kkutu.util.Validate;

import java.util.Objects;

public class User extends RoomPlayer implements IdentityProvider {
    private final String id;
    private final Profile profile;
    private final int money;
    private final Game game;

    public static User of(JsonObject json) {
        Validate.isTrue(json.has("id") && json.has("profile"), "Malformed user format. " + json.toString());
        return new User(
                json.get("id").getAsString(),
                Profile.of(json.get("profile").getAsJsonObject()),
                json.has("money") ? json.get("money").getAsInt() : 0,
                Game.of(json.get("game").getAsJsonObject())
        );
    }

    public User(String id, Profile profile, int money, Game game) {
        this.id = id;
        this.profile = profile;
        this.money = money;
        this.game = game;
    }

    @Override
    public String getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getMoney() {
        return money;
    }

    public Game getGame() {
        return game;
    }

    @Override
    public boolean isReady() {
        return getGame().isReady();
    }

    @Override
    protected void setReady0(boolean ready) {
        getGame().setReady(ready);
    }

    @Override
    public String getName() {
        return getProfile().getNick();
    }

    @Override
    public String toString() {
        return getProfile().getNick();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
