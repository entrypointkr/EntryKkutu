package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class GameStartPacket implements ReadablePacket {
    @Override
    public String type() {
        return "starting";
    }

    @Override
    public void read(JsonObject json) {

    }
}
