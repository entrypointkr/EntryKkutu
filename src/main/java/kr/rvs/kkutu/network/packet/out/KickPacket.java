package kr.rvs.kkutu.network.packet.out;

import com.google.gson.stream.JsonWriter;
import kr.rvs.kkutu.network.packet.WritablePacket;

import java.io.IOException;

public class KickPacket implements WritablePacket {
    private final boolean bot;
    private final String target;

    public KickPacket(boolean bot, String target) {
        this.bot = bot;
        this.target = target;
    }

    @Override
    public String type() {
        return "kick";
    }

    @Override
    public void write(JsonWriter writer) throws IOException {
        writer.name("robot").value(bot);
        writer.name("target").value(target);
    }
}
