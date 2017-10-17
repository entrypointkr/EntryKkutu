package kr.rvs.kkutu.factory.packet;

import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.network.packet.Packet;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public interface PacketFactory {
    Packet create(JsonObjectWrapper json);
}
