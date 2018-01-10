package kr.rvs.kkutu.network;

public class LobbyPacketManager extends PacketManager {
    private static final LobbyPacketManager MANAGER = new LobbyPacketManager();

    public static LobbyPacketManager get() {
        return MANAGER;
    }

    private LobbyPacketManager() {
    }
}
