package kr.rvs.kkutu.network.netty;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import kr.rvs.kkutu.network.PacketFactory;

import java.util.List;

public class PacketDecoder extends MessageToMessageDecoder<TextWebSocketFrame> {
    private static final JsonParser PARSER = new JsonParser();
    private final PacketFactory factory;

    public PacketDecoder(PacketFactory factory) {
        this.factory = factory;
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, TextWebSocketFrame msg, List<Object> out) throws Exception {
        JsonObject json = PARSER.parse(msg.text()).getAsJsonObject();
        out.add(factory.create(json));
    }
}
