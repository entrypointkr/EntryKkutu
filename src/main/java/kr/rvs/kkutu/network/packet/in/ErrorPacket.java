package kr.rvs.kkutu.network.packet.in;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.network.packet.ReadablePacket;

public class ErrorPacket implements ReadablePacket {
    private String code;

    @Override
    public String type() {
        return "error";
    }

    @Override
    public void read(JsonObject json) {
        this.code = json.get("code").getAsString();
    }

    public String getCode() {
        return code;
    }
}
