package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class YellPacket implements ReadablePacket {
    public String content;

    @Override
    public String type() {
        return "yell";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.content = json.get("value").getAsString();
    }
}
