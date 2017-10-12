package kr.rvs.kkutu.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import kr.rvs.kkutu.network.packet.EmptyPacket;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.ChatInPacket;
import kr.rvs.kkutu.network.packet.in.ConnectPacket;
import kr.rvs.kkutu.network.packet.in.DisconnectPacket;
import kr.rvs.kkutu.network.packet.in.RoomPacket;
import kr.rvs.kkutu.network.packet.in.WelcomePacket;
import kr.rvs.kkutu.util.Static;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;


/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class PacketManager {
    private static final Gson GSON = new GsonBuilder()
//            .registerTypeAdapterFactory(new PacketTypeAdapterFactory())
            .setPrettyPrinting()
            .create();
    private static final Map<String, Class<? extends Packet>> packetMap = new HashMap<>();
    private Channel channel;
    private final PacketHandlers handlers = new PacketHandlers();

    static {
        register(
                ChatInPacket.class, RoomPacket.class, WelcomePacket.class, DisconnectPacket.class, ConnectPacket.class
        );
    }

    private static Packet create(JsonObject json) {
        String type = json.get("type").getAsString();
        Class<? extends Packet> packetClass = packetMap.get(type);
        if (packetClass != null) {
            return GSON.fromJson(json, packetClass);
        } else {
            Static.log(Level.INFO, "Unknown packet type: " + type);
        }
        return new EmptyPacket();
    }

    private static Packet create(Class<? extends Packet> packetClass) {
        try {
            Constructor<? extends Packet> constructor = packetClass.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | InstantiationException e) {
            Static.log(e);
        }
        return new EmptyPacket();
    }

    @SafeVarargs
    private static void register(Class<? extends Packet>... packetClasses) {
        for (Class<? extends Packet> packetClass : packetClasses) {
            Packet packet = create(packetClass);
            packetMap.put(packet.type().internalName(), packetClass);
        }
    }

    public void receive(JsonObject json) {
        if (!json.has("type")) {
            Static.log(Level.WARNING, "Malformed json, " + json.toString());
            return;
        }

        Packet packet = create(json);
        handlers.notify(packet);
    }

    public void send(Packet packet) {
        JsonObject json = GSON.toJsonTree(packet).getAsJsonObject();
        json.addProperty("type", packet.type().internalName());
        channel.writeAndFlush(new TextWebSocketFrame(json.toString()));
    }

    public PacketHandlers getHandlers() {
        return handlers;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }
}
