package kr.rvs.kkutu.network.packet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import kr.rvs.kkutu.factory.game.GameFactory;
import kr.rvs.kkutu.factory.packet.PacketFactory;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.util.Servers;
import kr.rvs.kkutu.util.Static;

import java.util.logging.Level;


/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class PacketManager {
    private static final Gson GSON = new GsonBuilder().create();
    private final PacketHandlers handlers = new PacketHandlers();
    private final PacketFactory packetFactory;
    private final GameFactory gameFactory;
    private Channel channel;

    public PacketManager(PacketFactory packetFactory, GameFactory gameFactory) {
        this.packetFactory = packetFactory;
        this.gameFactory = gameFactory;
    }

    public PacketManager(Servers server) {
        this(server.getPacketFactory(), server.getGameFactory());
    }

    public void receive(JsonObject json) {
        if (!json.has("type")) {
            Static.log(Level.WARNING, "Malformed json, " + json.toString());
            return;
        }

        JsonObjectWrapper jsonWrapper = new JsonObjectWrapper(json, gameFactory);
        Packet packet = packetFactory.create(jsonWrapper);
        if (packet instanceof Readable) {
            ((Readable) packet).read(jsonWrapper);
        }
        handlers.notify(packet);
    }

    public void send(Packet packet) {
        JsonObject json = GSON.toJsonTree(packet).getAsJsonObject();
        json.addProperty("type", packet.type());
        if (packet instanceof Writable)
            ((Writable) packet).write(json);
        channel.writeAndFlush(new TextWebSocketFrame(json.toString()));
    }

    public void closeChannel() {
        channel.close();
    }

    public PacketHandlers getHandlers() {
        return handlers;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
