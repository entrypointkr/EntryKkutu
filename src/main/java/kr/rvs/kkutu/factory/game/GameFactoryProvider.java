package kr.rvs.kkutu.factory.game;

import kr.rvs.kkutu.holder.RoomHolder;
import kr.rvs.kkutu.holder.UserHolder;

/**
 * Created by Junhyeong Lim on 2017-10-19.
 */
public interface GameFactoryProvider {
    GameFactory get(UserHolder holder, RoomHolder roomHolder);
}
