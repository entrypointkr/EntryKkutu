package kr.rvs.kkutu.game.room;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.out.ChatOutPacket;
import kr.rvs.kkutu.network.packet.out.RoomReadyPacket;
import kr.rvs.kkutu.util.Gsons;

import java.util.*;

public class Room {
    private final String id;
    private final String channel;
    private String title;
    private boolean password;
    private int limit;
    private RoomMode mode;
    private int round;
    private int time;
    private String master;
    private Map<String, RoomPlayer> playerMap = Collections.synchronizedMap(new LinkedHashMap<>());
    private boolean ingame;
    private RoomGame game;

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
                json.get("gaming").getAsBoolean(),
                RoomGame.of(json.get("game").getAsJsonObject())
        );
    }

    public Room(String id, String channel, String title, boolean password, int limit, int mode, int round, int time, String master, Collection<RoomPlayer> players, boolean ingame, RoomGame game) {
        this.id = id;
        this.channel = channel;
        this.title = title;
        this.password = password;
        this.limit = limit;
        this.mode = RoomMode.values()[mode];
        this.round = round;
        this.time = time;
        this.master = master;
        this.ingame = ingame;
        this.game = game;

        players.forEach(player -> playerMap.put(player.getId(), player));
    }

    public RoomController getController() {
        return controller;
    }

    public void sendChat(String message, boolean relay) {
        packetManager.sendPacket(new ChatOutPacket(message, relay));
    }

    public Optional<RoomPlayer> getPlayer(String id) {
        return Optional.ofNullable(playerMap.get(id));
    }

    public RoomPlayer getPlayerOrThrow(String id) {
        return getPlayer(id).orElseThrow(IllegalStateException::new);
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
        this.game = room.game;

        this.playerMap.clear();
        this.playerMap.putAll(room.playerMap);
        updateController();
    }

    public void updateController() {
        if (controller != null) {
            controller.update();
        }
    }

    public int getPlayerAmount() {
        return playerMap.size();
    }

    public void ready() {
        packetManager.sendPacket(new RoomReadyPacket());
    }

    public boolean isJoinable() {
        return !isIngame() && getPlayerAmount() < limit;
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

    public RoomMode getMode() {
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

    public RoomGame getGame() {
        return game;
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

    public Collection<RoomPlayer> getPlayers() {
        return new ArrayList<>(playerMap.values());
    }

    @Override
    public String toString() {
        return '#' + getId() + ' ' + getTitle();
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
