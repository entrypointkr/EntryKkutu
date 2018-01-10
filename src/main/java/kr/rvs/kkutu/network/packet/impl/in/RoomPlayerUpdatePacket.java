package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.game.room.RoomBotPlayer;
import kr.rvs.kkutu.game.room.RoomPlayer;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class RoomPlayerUpdatePacket implements ReadablePacket {
    private RoomPlayer player;

    @Override
    public String type() {
        return "user";
    }

    @Override
    public void read(JsonObject json) {
        this.player = json.has("bot") ? RoomBotPlayer.of(json) : User.of(json);
    }

    public RoomPlayer getPlayer() {
        return player;
    }
}
