package kr.rvs.kkutu.network.impl;

import kr.rvs.kkutu.network.PacketFactory;
import kr.rvs.kkutu.network.packet.impl.*;

public class KkutuKoreaPacketFactory extends PacketFactory {
    public KkutuKoreaPacketFactory() {
        super(ChatInPacket::new, ConnectPacket::new, DisconnectPacket::new, RoomPacket::new, WelcomePacket::new, YellPacket::new);
    }
}
