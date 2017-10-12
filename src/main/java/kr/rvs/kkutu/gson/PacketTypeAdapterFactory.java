package kr.rvs.kkutu.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import kr.rvs.kkutu.network.packet.Packet;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class PacketTypeAdapterFactory implements TypeAdapterFactory {
    @Override
    @SuppressWarnings("unchecked")
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        Class<?> rawType = type.getRawType();
        if (Packet.class.isAssignableFrom(rawType)) {
            TypeAdapter<T> packetAdapter = gson.getDelegateAdapter(this, type);
            return (TypeAdapter<T>) new PacketTypeAdaptor((TypeAdapter<Packet>) packetAdapter);
        }
        return null;
    }
}
