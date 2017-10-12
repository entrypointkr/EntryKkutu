package kr.rvs.kkutu.gson;

import com.google.gson.*;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class JsonElementWrapper extends JsonElement {
    private final JsonElement element;

    public JsonElementWrapper(JsonElement element) {
        this.element = element;
    }

    public JsonObjectWrapper getAsJsonObjectWrapper() {
        return new JsonObjectWrapper(getAsJsonObject());
    }

    public String[] getAsStringArray() {
        JsonArray array = getAsJsonArray();
        String[] strArr = new String[array.size()];
        for (int i = 0; i < strArr.length; i++) {
            JsonElement element = array.get(i);
            if (!element.isJsonPrimitive())
                continue;

            strArr[i] = array.get(i).getAsString();
        }
        return strArr;
    }

    @Override
    public JsonElement deepCopy() {
        return element.deepCopy();
    }

    @Override
    public boolean isJsonArray() {
        return element.isJsonArray();
    }

    @Override
    public boolean isJsonObject() {
        return element.isJsonObject();
    }

    @Override
    public boolean isJsonPrimitive() {
        return element.isJsonPrimitive();
    }

    @Override
    public boolean isJsonNull() {
        return element.isJsonNull();
    }

    @Override
    public JsonObject getAsJsonObject() {
        return element.getAsJsonObject();
    }

    @Override
    public JsonArray getAsJsonArray() {
        return element.getAsJsonArray();
    }

    @Override
    public JsonPrimitive getAsJsonPrimitive() {
        return element.getAsJsonPrimitive();
    }

    @Override
    public JsonNull getAsJsonNull() {
        return element.getAsJsonNull();
    }

    @Override
    public boolean getAsBoolean() {
        return element.getAsBoolean();
    }

    @Override
    public Number getAsNumber() {
        return element.getAsNumber();
    }

    @Override
    public String getAsString() {
        return element.getAsString();
    }

    @Override
    public double getAsDouble() {
        return element.getAsDouble();
    }

    @Override
    public float getAsFloat() {
        return element.getAsFloat();
    }

    @Override
    public long getAsLong() {
        return element.getAsLong();
    }

    @Override
    public int getAsInt() {
        return element.getAsInt();
    }

    @Override
    public byte getAsByte() {
        return element.getAsByte();
    }

    @Override
    public char getAsCharacter() {
        return element.getAsCharacter();
    }

    @Override
    public BigDecimal getAsBigDecimal() {
        return element.getAsBigDecimal();
    }

    @Override
    public BigInteger getAsBigInteger() {
        return element.getAsBigInteger();
    }

    @Override
    public short getAsShort() {
        return element.getAsShort();
    }

    @Override
    public String toString() {
        return element.toString();
    }
}
