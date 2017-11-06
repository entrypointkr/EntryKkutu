package kr.rvs.kkutu.game;

import javafx.scene.image.Image;

/**
 * Created by Junhyeong Lim on 2017-10-19.
 */
public interface Player {
    String id();

    default Image image() {
        return new Image(getClass().getResourceAsStream("/image/def.png"));
    }

    String name();
}
