package kr.rvs.kkutu.game;

import com.google.gson.annotations.SerializedName;
import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class Profile {
    private final String id;
    @SerializedName("title")
    private final String name;
    @SerializedName("image")
    private final String imageUrl;

    public static Profile of(JsonObjectWrapper json) {
        String id = json.get("id").getAsString();
        String title = json.get("title").getAsString();
        String image = json.get("image").getAsString();
        return new Profile(id, title, image);
    }

    public Profile(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
