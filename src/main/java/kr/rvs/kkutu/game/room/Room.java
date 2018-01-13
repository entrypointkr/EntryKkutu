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
        this.mode = RoomMode.values()[mode];
        this.round = round;
        this.time = time;
        this.master = master;
        this.ingame = ingame;

        players.forEach(player -> playerMap.put(player.getId(), player));
    }

    public RoomController getController() {
        return controller;
    }

    public void sendChat(String message) {
        packetManager.sendPacket(new ChatOutPacket(message, isIngame()));
    }

    public Optional<RoomPlayer> getPlayer(String id) {
        return Optional.ofNullable(playerMap.get(id));
    }

    public RoomPlayer getPlayer(int index) {
        List<RoomPlayer> list = new ArrayList<>(playerMap.values());
        return list.get(index);
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
        refresh();
    }

    public void refresh() {
        if (controller != null) {
            controller.setPlayers(playerMap.values());
        }
    }

    public int getPlayerAmount() {
        return playerMap.size();
    }

//    public void join(RoomPlayer player) {
//        controller.join(player);
//        playerMap.put(player.getId(), player);
//    }
//
//    public void quit(String id) {
//        controller.quit(id);
//        playerMap.remove(id);
//    }

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
        return getTitle() + '#' + getId();
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
