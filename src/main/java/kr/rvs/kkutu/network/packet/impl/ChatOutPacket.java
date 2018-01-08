package kr.rvs.kkutu.network.packet.impl;

import com.google.gson.stream.JsonWriter;
import kr.rvs.kkutu.network.packet.WritablePacket;

import java.io.IOException;

public class ChatOutPacket implements WritablePacket {
    private final String value;

    public ChatOutPacket(String value) {
        this.value = value;
    }

    @Override
    public String type() {
        return "talk";
    }

    @Override
    public void write(JsonWriter writer) throws IOException {
        writer.name("value").value(value);
    }
}
