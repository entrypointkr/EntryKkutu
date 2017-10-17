package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class ConnectPacket implements ReadablePacket {
    public User user;

    @Override
    public String type() {
        return "conn";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.user = json.get("user").getAsGameObject(((wrapper, factory) -> factory.createUser(wrapper)));
    }
}
