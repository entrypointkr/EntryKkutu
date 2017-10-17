package kr.rvs.kkutu.game;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class GameOption {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GameOption option = (GameOption) o;

        if (manner != option.manner) return false;
        if (safe != option.safe) return false;
        if (injeong != option.injeong) return false;
        if (mission != option.mission) return false;
        if (loanword != option.loanword) return false;
        if (proverb != option.proverb) return false;
        if (strict != option.strict) return false;
        if (sami != option.sami) return false;
        return no2 == option.no2;
    }

    @Override
    public int hashCode() {
        int result = (manner ? 1 : 0);
        result = 31 * result + (safe ? 1 : 0);
        result = 31 * result + (injeong ? 1 : 0);
        result = 31 * result + (mission ? 1 : 0);
        result = 31 * result + (loanword ? 1 : 0);
        result = 31 * result + (proverb ? 1 : 0);
        result = 31 * result + (strict ? 1 : 0);
        result = 31 * result + (sami ? 1 : 0);
        result = 31 * result + (no2 ? 1 : 0);
        return result;
    }
}
