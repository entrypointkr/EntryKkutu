package kr.rvs.kkutu.network.packet.impl.out;

import com.google.gson.stream.JsonWriter;
import kr.rvs.kkutu.network.packet.WritablePacket;

import java.io.IOException;

public class RoomEnterPacket implements WritablePacket {
    private final String id;

    public RoomEnterPacket(String id) {
        this.id = id;
    }

    @Override
    public String type() {
        return "enter";
    }

    @Override
    public void write(JsonWriter writer) throws IOException {
        writer.name("id").value(id);
    }
}
