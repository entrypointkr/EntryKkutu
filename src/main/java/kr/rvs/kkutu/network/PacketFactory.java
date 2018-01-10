package kr.rvs.kkutu.network;

import kr.rvs.kkutu.network.packet.Packet;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public abstract class PacketFactory {
    private final Map<String, Supplier<? extends Packet>> packetSupplierTable = new HashMap<>();

    public static Packet createEmptyPacket() {
        return () -> "!empty";
    }

    @SafeVarargs
    public PacketFactory(Supplier<Packet>... suppliers) {
        for (Supplier<Packet> supplier : suppliers) {
            Packet packet = supplier.get();
            packetSupplierTable.put(packet.type(), supplier);
        }
    }

    public Packet create(String type) {
        return packetSupplierTable.getOrDefault(type, PacketFactory::createEmptyPacket).get();
    }
}
