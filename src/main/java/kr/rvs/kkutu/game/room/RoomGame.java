package kr.rvs.kkutu.game.room;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.IdentityProvider;
import kr.rvs.kkutu.util.Gsons;

import java.util.List;

public class RoomGame {
    private int round;
    private int turn;
    private List<String> players;

    public static RoomGame of(JsonObject json) {
        return new RoomGame(
                json.has("round") ? json.get("round").getAsInt() : 0,
                json.has("turn") ? json.get("turn").getAsInt() : 0,
                Gsons.remapJsonArray(json.get("seq").getAsJsonArray(), element -> element.isJsonObject()
                        ? element.getAsJsonObject().get("id").getAsString()
                        : element.getAsString())
        );
    }

    public RoomGame(int round, int turn, List<String> players) {
        this.round = round;
        this.turn = turn;
        this.players = players;
    }

    public boolean isTurn(IdentityProvider provider) {
        return players.size() > turn && players.get(turn).equals(provider.getId());
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public List<String> getPlayers() {
        return players;
    }
}
