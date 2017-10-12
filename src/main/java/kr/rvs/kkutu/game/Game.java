package kr.rvs.kkutu.game;

import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.util.Static;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class Game {
    public static Game of(JsonObjectWrapper json) {
        int round = json.get("round").getAsInt();
        int turn = json.get("turn").getAsInt();
        String[] seq = json.get("seq").getAsStringArray();
        String title = json.get("title").getAsString();
        String mission = json.get("mission").getAsString();

        return new Game(round, turn, seq, title, mission);
    }

    private final int round;
    private final int turn;
    private final Object[] seq;
    private final String title;
    private final String mission;

    public Game(int round, int turn, Object[] seq, String title, String mission) {
        this.round = round;
        this.turn = turn;
        this.seq = seq;
        this.title = title;
        this.mission = mission;
    }
}
