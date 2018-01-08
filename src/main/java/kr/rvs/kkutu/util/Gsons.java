package kr.rvs.kkutu.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Gsons {
    @SuppressWarnings("unchecked")
    public static <T> List<T> remapJsonArray(JsonArray array, Function<JsonElement, T> mapper) {
        List<T> ret = new ArrayList<>(array.size());
        for (int i = 0; i < array.size(); i++) {
            JsonElement element = array.get(i);
            ret.add(mapper.apply(element));
        }
        return ret;
    }

    private Gsons() {
    }
}
