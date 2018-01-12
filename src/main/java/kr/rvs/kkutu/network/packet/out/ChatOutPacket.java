package kr.rvs.kkutu.network.packet.out;

import com.google.gson.stream.JsonWriter;
import kr.rvs.kkutu.network.packet.WritablePacket;

import java.io.IOException;

public class ChatOutPacket implements WritablePacket {
    private final String value;
    private final boolean relay;

    public ChatOutPacket(String value, boolean relay) {
        this.value = value;
        this.relay = relay;
    }

    public ChatOutPacket(String value) {
        this(value, false);
    }

    @Override
    public String type() {
        return "talk";
    }

    @Override
    public void write(JsonWriter writer) throws IOException {
        writer.name("value").value(value);
        if (relay) {
            writer.name("relay").value(relay);
        }
    }
}
