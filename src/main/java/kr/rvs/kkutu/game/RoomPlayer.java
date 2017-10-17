package kr.rvs.kkutu.game;

/**
 * Created by Junhyeong Lim on 2017-10-13.
 */
public class RoomPlayer {
    private final String id;
    private final boolean robot;
    private final Game game;
    private final int level;
    private boolean ready;

    public RoomPlayer(String id, boolean robot, Game game, int level, boolean ready) {
        this.id = id;
        this.robot = robot;
        this.game = game;
        this.level = level;
        this.ready = ready;
    }
}
