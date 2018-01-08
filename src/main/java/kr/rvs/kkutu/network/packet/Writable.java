package kr.rvs.kkutu.network.packet;

import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public interface Writable {
    void write(JsonWriter writer) throws IOException;
}
