package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class DisconnectPacket implements ReadablePacket {
    public String id;

    @Override
    public String type() {
        return "disconn";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.id = json.get("id").getAsString();
    }
}
