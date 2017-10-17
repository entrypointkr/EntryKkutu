package kr.rvs.kkutu.network.packet;

import java.util.Optional;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public interface Packet {
    Packet EMPTY = () -> "empty";

    String type();

    @SuppressWarnings("unchecked")
    default <T extends Packet> Optional<T> cast(Class<T> packetClass) {
        return packetClass.isInstance(this) ? Optional.of((T) this) : Optional.empty();
    }
}
