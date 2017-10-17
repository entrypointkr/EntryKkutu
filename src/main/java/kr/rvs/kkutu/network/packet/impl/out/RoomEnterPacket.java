package kr.rvs.kkutu.network.packet.impl.out;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.WritablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class RoomEnterPacket implements WritablePacket {
    private final int id;

    public RoomEnterPacket(int id) {
        this.id = id;
    }

    @Override
    public String type() {
        return "enter";
    }

    @Override
    public void write(JsonObject json) {
        json.addProperty("id", this.id);
    }
}
