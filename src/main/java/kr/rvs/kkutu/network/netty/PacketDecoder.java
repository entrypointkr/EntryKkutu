package kr.rvs.kkutu.network.netty;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import kr.rvs.kkutu.network.PacketFactory;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.Readable;
import kr.rvs.kkutu.util.Gsons;
import kr.rvs.kkutu.util.Validate;

import java.util.List;

public class PacketDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    private final PacketFactory factory;

    public PacketDecoder(PacketFactory factory) {
        this.factory = factory;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        JsonObject json = Gsons.getParser().parse(msg.text()).getAsJsonObject();
        Validate.isTrue(json.has("type"), "Illegal packet format. " + json.toString());

        String type = json.get("type").getAsString();
        Packet packet = factory.create(type);
        if (packet instanceof Readable) {
            ((Readable) packet).read(json);
        }
        out.add(packet);
    }
}
