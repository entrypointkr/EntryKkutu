package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class RoomPacket implements ReadablePacket {
    public Room room;
    public String target;

    @Override
    public String type() {
        return "room";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.room = json.get("room").getAsGameObject(((wrapper, factory) -> factory.createRoom(wrapper)));
        this.target = json.get("target").getAsString();
    }
}
