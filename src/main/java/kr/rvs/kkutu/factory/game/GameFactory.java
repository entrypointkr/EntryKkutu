package kr.rvs.kkutu.factory.game;

import kr.rvs.kkutu.game.RoomInfo;
import kr.rvs.kkutu.game.GameOption;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.Bot;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gson.JsonObjectWrapper;

/**
 * Created by Junhyeong Lim on 2017-10-13.
 */
public interface GameFactory {
    Profile createProfile(JsonObjectWrapper json);

    User createUser(JsonObjectWrapper json);

    User.Data createUserData(JsonObjectWrapper json);

    User.Status createStatus(JsonObjectWrapper json);

    Room createRoom(JsonObjectWrapper json);

    Bot createRoomPlayer(JsonObjectWrapper json);

    RoomInfo createRoomInfo(JsonObjectWrapper json);

    GameOption createGameOption(JsonObjectWrapper json);
}
