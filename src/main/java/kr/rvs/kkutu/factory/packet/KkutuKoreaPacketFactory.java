package kr.rvs.kkutu.factory.packet;

import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.impl.in.ChatInPacket;
import kr.rvs.kkutu.network.packet.impl.in.ConnectPacket;
import kr.rvs.kkutu.network.packet.impl.in.DisconnectPacket;
import kr.rvs.kkutu.network.packet.impl.in.PreRoomPacket;
import kr.rvs.kkutu.network.packet.impl.in.RoomJoinPacket;
import kr.rvs.kkutu.network.packet.impl.in.RoomPacket;
import kr.rvs.kkutu.network.packet.impl.in.RoomQuitPacket;
import kr.rvs.kkutu.network.packet.impl.in.WelcomePacket;
import kr.rvs.kkutu.network.packet.impl.in.YellPacket;
import kr.rvs.kkutu.util.Static;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class KkutuKoreaPacketFactory implements PacketFactory {
    private final Map<String, Supplier<? extends Packet>> packetMap = new HashMap<>();

    public KkutuKoreaPacketFactory() {
        register(
                ChatInPacket.class, ConnectPacket.class, DisconnectPacket.class, PreRoomPacket.class, RoomPacket.class,
                WelcomePacket.class, YellPacket.class, RoomJoinPacket.class, RoomQuitPacket.class
        );
    }

    @SafeVarargs
    private final void register(Class<? extends Packet>... packetClasses) {
        for (Class<? extends Packet> packetClass : packetClasses) {
            try {
                Constructor<? extends Packet> constructor = packetClass.getConstructor();
                Packet packet = constructor.newInstance();
                packetMap.put(packet.type(), () -> {
                    try {
                        return constructor.newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                        throw new IllegalStateException(e);
                    }
                });
            } catch (Exception ex) {
                Static.log(ex);
            }
        }
    }

    @Override
    public Packet create(JsonObjectWrapper json) {
        Supplier<? extends Packet> supplier = packetMap.get(json.get("type").getAsString());
        return supplier != null ? supplier.get() : Packet.EMPTY;
    }
}
