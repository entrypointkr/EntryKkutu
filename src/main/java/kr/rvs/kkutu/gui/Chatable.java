package kr.rvs.kkutu.gui;

import kr.rvs.kkutu.game.Profile;

public interface Chatable {
    void chat(String message);

    void chat(Profile profile, String message);
}
