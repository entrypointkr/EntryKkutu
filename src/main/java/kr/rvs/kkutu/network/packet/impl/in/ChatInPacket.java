package kr.rvs.kkutu.network.packet.impl.in;

import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.ReadablePacket;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class ChatInPacket implements ReadablePacket {
    public Profile profile;
    public String content;

    @Override
    public String type() {
        return "chat";
    }

    @Override
    public void read(JsonObjectWrapper json) {
        this.profile = json.get("profile").getAsGameObject((wrapper, factory) -> factory.createProfile(wrapper));
        this.content = json.get("value").getAsString();

        profile.setName(json.get("nick").getAsString());
    }
}
