package kr.rvs.kkutu.factory.game;

import kr.rvs.kkutu.game.Game;
import kr.rvs.kkutu.game.GameOption;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.RoomPlayer;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-13.
 */
public interface GameFactory {
    Profile createProfile(JsonObjectWrapper json);

    User createUser(JsonObjectWrapper json);

    User.Data createUserData(JsonObjectWrapper json);

    Room createRoom(JsonObjectWrapper json);

    RoomPlayer createRoomPlayer(JsonObjectWrapper json);

    Game createGame(JsonObjectWrapper json);

    GameOption createGameOption(JsonObjectWrapper json);
}
