package kr.rvs.kkutu.network.packet.impl.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;
import kr.rvs.kkutu.util.Validate;

public class YellPacket implements ReadablePacket {
    private String value;

    @Override
    public String type() {
        return "yell";
    }

    @Override
    public void read(JsonObject json) {
        Validate.isTrue(json.has("value"));
        this.value = json.get("value").getAsString();
    }

    public String getValue() {
        return value;
    }
}
