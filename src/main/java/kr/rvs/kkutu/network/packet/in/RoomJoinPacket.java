package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class RoomJoinPacket implements ReadablePacket {
    private User user;

    @Override
    public String type() {
        return "connRoom";
    }

    @Override
    public void read(JsonObject json) {
        this.user = User.of(json.get("user").getAsJsonObject());
    }

    public User getUser() {
        return user;
    }
}
