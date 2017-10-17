package kr.rvs.kkutu.game;

import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.util.Server;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class Profile {
    private final String id;
    @SerializedName("image")
    private final String imageUrl;
    @SerializedName("title")
    private String name;

    public Profile(String id, String name, String imageUrl) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public static Profile of(JsonObjectWrapper json) {
        String id = json.get("id").getAsString();
        String title = json.get("title").getAsString();
        String image = json.get("image").getAsString();
        return new Profile(id, title, image);
    }

    public Image getImage(String baseUrl) {
        return new Image(baseUrl + imageUrl);
    }

    public Image getImage(Server server) {
        return getImage(server.getServer().getStringURL());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
