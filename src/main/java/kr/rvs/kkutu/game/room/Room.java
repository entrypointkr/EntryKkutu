package kr.rvs.kkutu.game.room;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.impl.out.RoomReadyPacket;
import kr.rvs.kkutu.util.Gsons;

import java.util.*;

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
    private Map<String, RoomPlayer> playerMap = Collections.synchronizedMap(new LinkedHashMap<>());
    private boolean ingame;

    private RoomController controller;
    private PacketManager packetManager;

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
                Gsons.remapJsonArray(json.get("players").getAsJsonArray(), RoomPlayer::get),
                json.get("gaming").getAsBoolean()
        );
    }

    public Room(String id, String channel, String title, boolean password, int limit, int mode, int round, int time, String master, Collection<RoomPlayer> players, boolean ingame) {
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

        players.forEach(player -> playerMap.put(player.getId(), player));
    }

    public void chat(Profile profile, String message) {
        controller.chat(profile, message);
    }

    public Optional<RoomPlayer> getPlayer(String id) {
        return Optional.ofNullable(playerMap.get(id));
    }

    public void update(Room room) {
        this.title = room.title;
        this.password = room.password;
        this.limit = room.limit;
        this.mode = room.mode;
        this.round = room.round;
        this.time = room.time;
        this.master = room.master;
        this.ingame = room.ingame;

        this.playerMap.clear();
        this.playerMap.putAll(room.playerMap);
    }

    public void join(RoomPlayer player) {
        controller.join(player);
        playerMap.put(player.getId(), player);
    }

    public void quit(String id) {
        controller.quit(id);
        playerMap.remove(id);
    }

    public void ready() {
        packetManager.sendPacket(new RoomReadyPacket());
    }

    public boolean isJoinable() {
        return !isIngame() && playerMap.size() < limit;
    }

    public boolean isEmpty() {
        return playerMap.isEmpty();
    }

    public boolean isMaster(String id) {
        return master.equals(id);
    }

    public boolean isMaster(RoomPlayer player) {
        return isMaster(player.getId());
    }

    public void roomInit(RoomController controller) {
        playerMap.values().forEach(controller::join);
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

    public boolean isIngame() {
        return ingame;
    }

    public void setController(RoomController controller) {
        this.controller = controller;
    }

    public void setPacketManager(PacketManager manager) {
        this.packetManager = manager;
    }

    public PacketManager getPacketManager() {
        return packetManager;
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
