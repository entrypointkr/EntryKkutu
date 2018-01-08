package kr.rvs.kkutu.network.packet;

import com.google.gson.JsonObject;

public interface Readable {
    void read(JsonObject json);
}
