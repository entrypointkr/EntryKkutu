package kr.rvs.kkutu.gson;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import kr.rvs.kkutu.factory.game.GameFactory;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class JsonElementWrapper extends JsonElement {
    private final JsonElement element;
    private final GameFactory gameFactory;

    public JsonElementWrapper(JsonElement element, GameFactory gameFactory) {
        this.element = element;
        this.gameFactory = gameFactory;
    }

    public JsonObjectWrapper getAsJsonObjectWrapper() {
        return new JsonObjectWrapper(getAsJsonObject(), gameFactory);
    }

    public <T> List<T> getAsSpecificTypeArray(Function<JsonElementWrapper, T> remapper) {
        JsonArray array = getAsJsonArray();
        List<T> ret = new ArrayList<>(array.size());
        for (JsonElement element : array) {
            ret.add(remapper.apply(new JsonElementWrapper(element, gameFactory)));
        }
        return ret;
    }

    public <T> T getAsSpecificType(Function<JsonElementWrapper, T> mapper) {
        return mapper.apply(this);
    }

    public <T> T getAsGameObject(GameObjectMapper<T> mapper) {
        return mapper.apply(getAsJsonObjectWrapper(), gameFactory);
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

    public JsonElement getElement() {
        return element;
    }

    public interface GameObjectMapper<T> {
        T apply(JsonObjectWrapper wrapper, GameFactory factory);
    }
}
