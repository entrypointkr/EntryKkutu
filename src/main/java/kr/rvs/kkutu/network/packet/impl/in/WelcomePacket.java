package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gson.JsonElementWrapper;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class WelcomePacket implements ReadablePacket {
    public String id;
    public Map<Integer, Room> roomMap = new HashMap<>();
    public Map<String, User> userMap = new HashMap<>();

    @Override
    public String type() {
        return "welcome";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.id = json.get("id").getAsString();

        JsonObjectWrapper users = json.get("users").getAsJsonObjectWrapper();
        JsonObjectWrapper rooms = json.get("rooms").getAsJsonObjectWrapper();
        for (Map.Entry<String, JsonElementWrapper> entry : users.entrySet()) {
            User user = entry.getValue().getAsGameObject((wrapper, factory) -> factory.createUser(wrapper));
            this.userMap.put(user.getId(), user);
        }
        for (Map.Entry<String, JsonElementWrapper> entry : rooms.entrySet()) {
            Room room = entry.getValue().getAsGameObject((wrapper, factory) -> factory.createRoom(wrapper));
            this.roomMap.put(room.getId(), room);
        }
    }
}
