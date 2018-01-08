package kr.rvs.kkutu.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.util.Validate;

import java.util.Objects;

public class User {
    private final String id;
    private final Profile profile;
    private final int money;

    public static User of(JsonObject json) {
        Validate.isTrue(json.has("id") && json.has("profile"), "Malformed user format.");
        return new User(
                json.get("id").getAsString(),
                Profile.of(json.get("profile").getAsJsonObject()),
                json.get("money").getAsInt()
        );
    }

    public User(String id, Profile profile, int money) {
        this.id = id;
        this.profile = profile;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getMoney() {
        return money;
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
