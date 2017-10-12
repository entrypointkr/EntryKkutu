package kr.rvs.kkutu.game;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class Room {
//    public static Room of(JsonObjectWrapper json) {
//        int id = json.get("id").getAsInt();
//        String channel = json.get("channel").getAsString();
//        String title = json.get("title").getAsString();
//        boolean password = json.get("password").getAsBoolean();
//        int limit = json.get("limit").getAsInt();
//        int mode = json.get("mode").getAsInt();
//        int round = json.get("round").getAsInt();
//        int time = json.get("time").getAsInt();
//        String master = json.get("master").getAsString();
//        List<String> players = Arrays.asList(json.get("players").getAsStringArray());
//        boolean ingame = json.get("gaming").getAsBoolean();
//        Game game = Game.of(json.get("game").getAsJsonObjectWrapper());
//        GameOption option = GameOption.of(json.get("opts").getAsJsonObjectWrapper());
//
//        return new Room(id, channel, title, password, limit, mode, round, time, master, players, ingame, game, option);
//    }

    private final int id;
    private final String channel;
    private final String title;
    private final boolean password;
    private final int limit;
    private final int mode;
    private final int round;
    private final int time;
    private final String master;
    private final List<Object> players;
    @SerializedName("gaming")
    private final boolean ingame;
    private final Game game;
    @SerializedName("opts")
    private final GameOption option;

    public Room(int id, String channel, String title, boolean password, int limit, int mode, int round, int time, String master, List<Object> players, boolean ingame, Game game, GameOption option) {
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
        this.game = game;
        this.option = option;
    }

    public int getId() {
        return id;
    }

    public String getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public boolean isPrivate() {
        return password;
    }

    public int getLimit() {
        return limit;
    }

    public RoomType getMode() {
        return RoomType.getById(mode);
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

    public List<Object> getPlayers() {
        return players;
    }

    public boolean isIngame() {
        return ingame;
    }

    public Game getGame() {
        return game;
    }

    public GameOption getOption() {
        return option;
    }
}
