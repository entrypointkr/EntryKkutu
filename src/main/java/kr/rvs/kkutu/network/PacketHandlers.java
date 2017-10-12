package kr.rvs.kkutu.network;

import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class PacketHandlers {
    private final Set<PacketHandler> handlers = new HashSet<>();

    public void notify(Packet packet) {
        for (PacketHandler handler : handlers) {
            handler.handle(packet);
        }
    }

    public PacketHandlers add(PacketHandler... handlers) {
        this.handlers.addAll(Arrays.asList(handlers));
        return this;
    }
}
