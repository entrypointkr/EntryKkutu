package kr.rvs.kkutu.network.packet;

import com.google.gson.JsonObject;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public interface Writable {
    void write(JsonObject json);
}
