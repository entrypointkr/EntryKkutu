package kr.rvs.kkutu.network;

import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.network.handler.RoomHandler;

public class RoomPacketManager extends PacketManager {
    private final Room room;

    public RoomPacketManager(Room room) {
        this.room = room;
        addHandler(new RoomHandler(room));
    }
}
