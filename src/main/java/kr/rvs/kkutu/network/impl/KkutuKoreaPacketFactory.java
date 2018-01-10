package kr.rvs.kkutu.network.impl;

import kr.rvs.kkutu.network.PacketFactory;
import kr.rvs.kkutu.network.packet.impl.in.*;

public class KkutuKoreaPacketFactory extends PacketFactory {
    private static final KkutuKoreaPacketFactory INSTANCE = new KkutuKoreaPacketFactory();

    public static KkutuKoreaPacketFactory get() {
        return INSTANCE;
    }

    private KkutuKoreaPacketFactory() {
        super(ChatInPacket::new, ConnectPacket::new, DisconnectPacket::new, RoomPacket::new, WelcomePacket::new, YellPacket::new, PreRoomPacket::new,
                RoomJoinPacket::new, RoomQuitPacket::new, RoomPlayerUpdatePacket::new,
                GameReadyPacket::new, GameStartPacket::new, GameTurnStartPacket::new, GameTurnEndPacket::new, GameTurnErrorPacket::new);
    }
}
