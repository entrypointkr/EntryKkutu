package kr.rvs.kkutu.game;

import javafx.scene.image.Image;

/**
 * Created by Junhyeong Lim on 2017-10-13.
 */
public class Bot implements Player {
    private final String id;
    private final boolean robot;
    private final RoomInfo game;
    private final int level;
    private boolean ready;

    public Bot(String id, boolean robot, RoomInfo game, int level, boolean ready) {
        this.id = id;
        this.robot = robot;
        this.game = game;
        this.level = level;
        this.ready = ready;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Image image() {
        return null;
    }

    @Override
    public String name() {
        return null;
    }
}
