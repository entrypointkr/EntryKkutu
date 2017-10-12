package kr.rvs.kkutu.game;

import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class User {
    private final String id;
    private final boolean guest;
    private final Game game;
    private final Profile profile;
    private final int money;

    public static User of(JsonObjectWrapper json) {
        String id = json.get("id").getAsString();
        boolean guest = json.get("guest").getAsBoolean();
        Game game = Game.of(json.get("game").getAsJsonObjectWrapper());
        Profile profile = Profile.of(json.get("profile").getAsJsonObjectWrapper());
        int money = json.get("money").getAsInt();
        return new User(id, guest, game, profile, money);
    }

    public User(String id, boolean guest, Game game, Profile profile, int money) {
        this.id = id;
        this.guest = guest;
        this.game = game;
        this.profile = profile;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public boolean isGuest() {
        return guest;
    }

    public Game getGame() {
        return game;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getMoney() {
        return money;
    }
}
