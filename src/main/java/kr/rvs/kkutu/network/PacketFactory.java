package kr.rvs.kkutu.network;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.Readable;
import kr.rvs.kkutu.util.Validate;

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

    private Packet create(String type) {
        return packetSupplierTable.getOrDefault(type, PacketFactory::createEmptyPacket).get();
    }

    public Packet create(JsonObject json) {
        Validate.isTrue(json.has("type"), "Illegal packet format.");
        Packet packet = create(json.get("type").getAsString());
        if (packet instanceof Readable) {
            ((Readable) packet).read(json);
        }
        return packet;
    }
}
