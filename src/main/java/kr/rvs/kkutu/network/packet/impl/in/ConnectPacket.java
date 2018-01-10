package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class ConnectPacket implements ReadablePacket {
    private User user;
    @Override
    public String type() {
        return "conn";
    }

    @Override
    public void read(JsonObject json) {
        this.user = User.of(json.get("user").getAsJsonObject());
    }

    public User getUser() {
        return user;
    }
}
