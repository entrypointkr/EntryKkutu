package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class PreRoomPacket implements ReadablePacket {
    public int channel;
    public int id;

    @Override
    public String type() {
        return "preRoom";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.channel = json.get("channel").getAsInt();
        this.id = json.get("id").getAsInt();
    }
}
