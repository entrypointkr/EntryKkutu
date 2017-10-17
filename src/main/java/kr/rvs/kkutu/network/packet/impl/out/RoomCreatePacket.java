package kr.rvs.kkutu.network.packet.impl.out;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.WritablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class RoomCreatePacket implements WritablePacket {
    @Override
    public String type() {
        return "enter";
    }

    @Override
    public void write(JsonObject json) {
        // TODO
    }
}
