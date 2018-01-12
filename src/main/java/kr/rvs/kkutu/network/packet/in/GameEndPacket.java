package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameEndPacket implements ReadablePacket {
    @Override
    public String type() {
        return "roundEnd";
    }

    @Override
    public void read(JsonObject json) {

    }
}
