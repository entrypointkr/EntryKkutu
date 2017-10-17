package kr.rvs.kkutu.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import kr.rvs.kkutu.factory.game.GameFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
// TODO: Proxy pattern
public class JsonObjectWrapper {
    private final JsonObject object;
    private final GameFactory gameFactory;

    public JsonObjectWrapper(JsonObject object, GameFactory gameFactory) {
        this.object = object;
        this.gameFactory = gameFactory;
    }

    public JsonElementWrapper get(String key) {
        JsonElement element = object.get(key);
        if (element == null || element instanceof JsonNull)
            element = JsonNullSafer.INST;
        return new JsonElementWrapper(element, gameFactory);
    }

    public Set<Map.Entry<String, JsonElementWrapper>> entrySet() {
        Set<Map.Entry<String, JsonElementWrapper>> ret = new HashSet<>();
        for (Map.Entry<String, JsonElement> entry : object.entrySet()) {
            ret.add(new EntryWrapper<>(entry));
        }
        return ret;
    }

    static class JsonNullSafer extends JsonElement {
        public static final JsonNullSafer INST = new JsonNullSafer();

        @Override
        public JsonElement deepCopy() {
            return INST;
        }

        @Override
        public JsonObject getAsJsonObject() {
            return new JsonObject();
        }

        @Override
        public JsonArray getAsJsonArray() {
            return new JsonArray();
        }

        @Override
        public JsonPrimitive getAsJsonPrimitive() {
            return super.getAsJsonPrimitive();
        }

        @Override
        public JsonNull getAsJsonNull() {
            return JsonNull.INSTANCE;
        }

        @Override
        public boolean getAsBoolean() {
            return false;
        }

        @Override
        public Number getAsNumber() {
            return null;
        }

        @Override
        public String getAsString() {
            return null;
        }

        @Override
        public double getAsDouble() {
            return -1;
        }

        @Override
        public float getAsFloat() {
            return -1;
        }

        @Override
        public long getAsLong() {
            return -1;
        }

        @Override
        public int getAsInt() {
            return -1;
        }

        @Override
        public byte getAsByte() {
            return -1;
        }

        @Override
        public char getAsCharacter() {
            return '\0';
        }

        @Override
        public BigDecimal getAsBigDecimal() {
            return null;
        }

        @Override
        public BigInteger getAsBigInteger() {
            return null;
        }

        @Override
        public short getAsShort() {
            return -1;
        }
    }

    class EntryWrapper<K> implements Map.Entry<K, JsonElementWrapper> {
        private final Map.Entry<K, JsonElement> delegate;

        public EntryWrapper(Map.Entry<K, JsonElement> delegate) {
            this.delegate = delegate;
        }

        @Override
        public K getKey() {
            return delegate.getKey();
        }

        @Override
        public JsonElementWrapper getValue() {
            return new JsonElementWrapper(delegate.getValue(), gameFactory);
        }

        @Override
        public JsonElementWrapper setValue(JsonElementWrapper value) {
            JsonElement old = delegate.setValue(value.getElement());
            return new JsonElementWrapper(old, gameFactory);
        }
    }
}
