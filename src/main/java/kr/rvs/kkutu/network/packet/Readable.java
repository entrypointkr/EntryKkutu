package kr.rvs.kkutu.network.packet;

import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public interface Readable {
    void read(JsonObjectWrapper json);
}
