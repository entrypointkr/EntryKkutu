package kr.rvs.kkutu.network.packet.out;

import com.google.gson.stream.JsonWriter;
import kr.rvs.kkutu.network.packet.WritablePacket;

import java.io.IOException;

public class RoomEnterPacket implements WritablePacket {
    private final String id;
    private final String password;

    public RoomEnterPacket(String id, String password) {
        this.id = id;
        this.password = password;
    }

    public RoomEnterPacket(String id) {
        this(id, null);
    }

    @Override
    public String type() {
        return "enter";
    }

    @Override
    public void write(JsonWriter writer) throws IOException {
        writer.name("id").value(id);
        if (password != null) {
            writer.name("password").value(password);
        }
    }
}
