package kr.rvs.kkutu.game;

import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class GameOption {
    public static GameOption of(JsonObjectWrapper json) {
        boolean manner = json.get("manner").getAsBoolean();
        boolean safe = json.get("safe").getAsBoolean();
        boolean injeong = json.get("injeong").getAsBoolean();
        boolean mission = json.get("mission").getAsBoolean();
        boolean loanword = json.get("loanword").getAsBoolean();
        boolean proverb = json.get("proverb").getAsBoolean();
        boolean strict = json.get("strict").getAsBoolean();
        boolean sami = json.get("sami").getAsBoolean();
        boolean no2 = json.get("no2").getAsBoolean();

        return new GameOption(manner, safe, injeong, mission, loanword, proverb, strict, sami, no2);
    }

    private final boolean manner;
    private final boolean safe;
    private final boolean injeong;
    private final boolean mission;
    private final boolean loanword;
    private final boolean proverb;
    private final boolean strict;
    private final boolean sami;
    private final boolean no2;

    public GameOption(boolean manner, boolean safe, boolean injeong, boolean mission, boolean loanword, boolean proverb, boolean strict, boolean sami, boolean no2) {
        this.manner = manner;
        this.safe = safe;
        this.injeong = injeong;
        this.mission = mission;
        this.loanword = loanword;
        this.proverb = proverb;
        this.strict = strict;
        this.sami = sami;
        this.no2 = no2;
    }
}
