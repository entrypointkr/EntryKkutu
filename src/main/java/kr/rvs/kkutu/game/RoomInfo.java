package kr.rvs.kkutu.game;

import java.util.List;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class RoomInfo {
    private final int round;
    private final int turn;
    private final List<Object> seq;
    private final String title;
    private final String mission;

    public RoomInfo(int round, int turn, List<Object> seq, String title, String mission) {
        this.round = round;
        this.turn = turn;
        this.seq = seq;
        this.title = title;
        this.mission = mission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomInfo game = (RoomInfo) o;

        if (round != game.round) return false;
        if (turn != game.turn) return false;
        if (seq != null ? !seq.equals(game.seq) : game.seq != null) return false;
        if (title != null ? !title.equals(game.title) : game.title != null) return false;
        return mission != null ? mission.equals(game.mission) : game.mission == null;
    }

    @Override
    public int hashCode() {
        int result = round;
        result = 31 * result + turn;
        result = 31 * result + (seq != null ? seq.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (mission != null ? mission.hashCode() : 0);
        return result;
    }
}
