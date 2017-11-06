package kr.rvs.kkutu.game;

import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class Room {
    private final int id;
    private final String channel;
    private final String title;
    private final boolean password;
    private final int limit;
    private final int mode;
    private final int round;
    private final int time;
    private final String master;
    private final Map<String, User> playerMap = new HashMap<>();
    @SerializedName("gaming")
    private final boolean ingame;
    private final RoomInfo game;
    @SerializedName("opts")
    private final GameOption option;

    public Room(int id, String channel, String title, boolean password, int limit, int mode, int round, int time, String master, boolean ingame, RoomInfo game, GameOption option) {
        this.id = id;
        this.channel = channel;
        this.title = title;
        this.password = password;
        this.limit = limit;
        this.mode = mode;
        this.round = round;
        this.time = time;
        this.master = master;
        this.ingame = ingame;
        this.game = game;
        this.option = option;
    }

    public void addUser(User user) {
        if (user != null)
            this.playerMap.put(user.getId(), user);
    }

    public String getTotalPlayersStr() {
        return getPlayerMap().size() + "/" + getLimit();
    }

    public boolean isJoinable() {
        return !isIngame() && playerMap.size() < limit;
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

    public GameType getMode() {
        return GameType.getById(mode);
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

    public Map<String, User> getPlayerMap() {
        return playerMap;
    }

    public boolean isIngame() {
        return ingame;
    }

    public RoomInfo getGame() {
        return game;
    }

    public GameOption getOption() {
        return option;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;

        return id == room.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
