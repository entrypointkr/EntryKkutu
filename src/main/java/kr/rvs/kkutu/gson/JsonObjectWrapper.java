package kr.rvs.kkutu.gson;

import com.google.gson.*;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class JsonObjectWrapper {
    private final JsonObject object;

    public JsonObjectWrapper(JsonObject object) {
        this.object = object;
    }

    public JsonElementWrapper get(String key) {
        JsonElement element = object.get(key);
        if (element == null || element instanceof JsonNull)
            element = JsonNullSafer.INST;
        return new JsonElementWrapper(element);
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
}
