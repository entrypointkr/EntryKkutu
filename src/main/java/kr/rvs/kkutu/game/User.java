package kr.rvs.kkutu.game;

import javafx.scene.image.Image;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class User implements Player {
    private final String id;
    private final Data data;
    private final boolean guest;
    private final Status game;
    private final Profile profile;
    private final int money;

    public User(String id, Data data, boolean guest, Status game, Profile profile, int money) {
        this.id = id;
        this.data = data;
        this.guest = guest;
        this.game = game;
        this.profile = profile;
        this.money = money;
    }

    @Override
    public String id() {
        return id;
    }

    @Override
    public Image image() {
        return getProfile().getImage();
    }

    @Override
    public String name() {
        return getProfile().getName();
    }

    public Data getData() {
        return data;
    }

    public boolean isGuest() {
        return guest;
    }

    public Status getGame() {
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

    public static class Status {
        private final boolean ready;
        private final int team;
        private final int practice;

        public Status(boolean ready, int team, int practice) {
            this.ready = ready;
            this.team = team;
            this.practice = practice;
        }

        public boolean isReady() {
            return ready;
        }

        public int getTeam() {
            return team;
        }

        public int getPractice() {
            return practice;
        }
    }
}
