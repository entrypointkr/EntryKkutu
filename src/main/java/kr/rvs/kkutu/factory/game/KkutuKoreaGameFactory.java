package kr.rvs.kkutu.factory.game;

import com.google.gson.JsonObject;
import kr.rvs.kkutu.game.GameOption;
import kr.rvs.kkutu.game.Profile;
import kr.rvs.kkutu.game.Room;
import kr.rvs.kkutu.game.RoomInfo;
import kr.rvs.kkutu.game.Bot;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gson.JsonObjectWrapper;
import kr.rvs.kkutu.holder.UserHolder;

import java.util.List;

/**
 * Created by Junhyeong Lim on 2017-10-13.
 */
public class KkutuKoreaGameFactory implements GameFactory {

    @Override
    public Profile createProfile(JsonObjectWrapper json) {
        String id = json.get("id").getAsString();
        String title = json.get("nick").getAsString();
        String image = json.get("image").getAsString();
        return new Profile(id, title, image);
    }

    @Override
    public User createUser(JsonObjectWrapper json) {
        JsonObject handle = json.getHandle();
        if (handle.isJsonPrimitive()) {
            return UserHolder.getInst().get(handle.getAsString()).orElse(null);
        }
        String id = json.get("id").getAsString();
        User.Data data = createUserData(json.get("data").getAsJsonObjectWrapper());
        boolean guest = json.get("guest").getAsBoolean();
        User.Status game = createStatus(json.get("game").getAsJsonObjectWrapper());
        Profile profile = Profile.of(json.get("profile").getAsJsonObjectWrapper());
        profile.setName(json.get("nick").getAsString());
        int money = json.get("money").getAsInt();
        return new User(id, data, guest, game, profile, money);
    }

    @Override
    public User.Data createUserData(JsonObjectWrapper json) {
        int connectDate = json.get("connectDate").getAsInt();
        int playTime = json.get("playTime").getAsInt();
        int score = json.get("score").getAsInt();
        return new User.Data(connectDate, playTime, score);
    }

    @Override
    public User.Status createStatus(JsonObjectWrapper json) {
        boolean ready = json.get("ready").getAsBoolean();
        int team = json.get("team").getAsInt();
        int practice = json.get("practice").getAsInt();
        return new User.Status(ready, team, practice);
    }

    @Override
    public Room createRoom(JsonObjectWrapper json) {
        int id = json.get("id").getAsInt();
        String channel = json.get("channel").getAsString();
        String title = json.get("title").getAsString();
        boolean password = json.get("password").getAsBoolean();
        int limit = json.get("limit").getAsInt();
        int mode = json.get("mode").getAsInt();
        int round = json.get("round").getAsInt();
        int time = json.get("time").getAsInt();
        String master = json.get("master").getAsString();
        boolean ingame = json.get("gaming").getAsBoolean();
        RoomInfo game = json.get("game").getAsGameObject((wrapper, factory) -> factory.createRoomInfo(wrapper));
        GameOption option = json.get("opts").getAsGameObject((wrapper, factory) -> factory.createGameOption(wrapper));

        Room ret = new Room(id, channel, title, password, limit, mode, round, time, master, ingame, game, option);
        json.get("players").getAsSpecificTypeArray(element -> {
            if (element.isJsonPrimitive()) {
                return UserHolder.getInst().get(element.getAsString()).orElse(null);
            } else {
                JsonObjectWrapper bot = element.getAsJsonObjectWrapper();
                String botId = bot.get("id").getAsString();
                return new User(
                        botId,
                        createUserData(bot),
                        false,
                        new User.Status(true, 0, 0),
                        new Profile(botId, "봇", UserHolder.getInst().getUsers().get(0).getProfile().getImageUrl()),
                        0
                );
            }
        }).forEach(ret::addUser);
        return new Room(id, channel, title, password, limit, mode, round, time, master, ingame, game, option);
    }

    @Override
    public Bot createRoomPlayer(JsonObjectWrapper json) {
        String id = json.get("id").getAsString();
        boolean robot = json.get("robot").getAsBoolean();
        RoomInfo game = json.get("game").getAsGameObject((wrapper, factory) -> factory.createRoomInfo(wrapper));
        int level = json.get("level").getAsInt();
        boolean ready = json.get("ready").getAsBoolean();
        return new Bot(id, robot, game, level, ready);
    }

    @Override
    public RoomInfo createRoomInfo(JsonObjectWrapper json) {
        int round = json.get("round").getAsInt();
        int turn = json.get("turn").getAsInt();
        List<Object> seq = json.get("seq").getAsSpecificTypeArray(element ->
                element.isJsonPrimitive() ? element.getAsString() : element.getAsGameObject((wrapper, factory) -> factory.createRoomPlayer(wrapper)));
        String title = json.get("title").getAsString();
        String mission = json.get("mission").getAsString();

        return new RoomInfo(round, turn, seq, title, mission);
    }

    @Override
    public GameOption createGameOption(JsonObjectWrapper json) {
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
}
