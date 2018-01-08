package kr.rvs.kkutu.network.netty;

import com.google.gson.stream.JsonWriter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.Writable;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

public class PacketEncoder extends MessageToMessageEncoder<Packet> {
    private void write(Packet msg, JsonWriter writer) throws IOException {
        writer.name("type").value(msg.type());
        if (msg instanceof Writable) {
            ((Writable) msg).write(writer);
        }
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        StringWriter stringWriter = new StringWriter();
        JsonWriter writer = new JsonWriter(stringWriter);
        writer.setLenient(true);

        writer.beginObject();
        write(msg, writer);
        writer.endObject();

        out.add(new TextWebSocketFrame(stringWriter.toString()));
    }
}
