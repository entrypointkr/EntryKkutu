package kr.rvs.kkutu.factory.game;

import kr.rvs.kkutu.game.Game;
import kr.rvs.kkutu.game.GameOption;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.RoomPlayer;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-15.
 */
public class KkutuIoGameFactory implements GameFactory {
    @Override
    public Profile createProfile(JsonObjectWrapper json) {
        return null;
    }

    @Override
    public User createUser(JsonObjectWrapper json) {
        return null;
    }

    @Override
    public User.Data createUserData(JsonObjectWrapper json) {
        return null;
    }

    @Override
    public Room createRoom(JsonObjectWrapper json) {
        return null;
    }

    @Override
    public RoomPlayer createRoomPlayer(JsonObjectWrapper json) {
        return null;
    }

    @Override
    public Game createGame(JsonObjectWrapper json) {
        return null;
    }

    @Override
    public GameOption createGameOption(JsonObjectWrapper json) {
        return null;
    }
}
