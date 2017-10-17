package kr.rvs.kkutu.game;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class User {
    private final String id;
    private final Data data;
    private final boolean guest;
    private final Game game;
    private final Profile profile;
    private final int money;

    public User(String id, Data data, boolean guest, Game game, Profile profile, int money) {
        this.id = id;
        this.data = data;
        this.guest = guest;
        this.game = game;
        this.profile = profile;
        this.money = money;
    }

    public String getId() {
        return id;
    }

    public Data getData() {
        return data;
    }

    public boolean isGuest() {
        return guest;
    }

    public Game getGame() {
        return game;
    }

    public Profile getProfile() {
        return profile;
    }

    public int getMoney() {
        return money;
    }

    public static class Data {
        private int connectData;
        private int playTime;
        private int score;

        public Data(int connectData, int playTime, int score) {
            this.connectData = connectData;
            this.playTime = playTime;
            this.score = score;
        }

        public int getConnectData() {
            return connectData;
        }

        public void setConnectData(int connectData) {
            this.connectData = connectData;
        }

        public int getPlayTime() {
            return playTime;
        }

        public void setPlayTime(int playTime) {
            this.playTime = playTime;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }
    }
}
