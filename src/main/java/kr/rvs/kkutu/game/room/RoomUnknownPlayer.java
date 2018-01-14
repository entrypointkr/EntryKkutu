package kr.rvs.kkutu.game.room;

import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.game.holder.UserHolder;

import java.util.Optional;

public class RoomUnknownPlayer extends RoomPlayer {
    public static final RoomPlayer DEFAULT = new RoomPlayer() {
        @Override
        public String getId() {
            return "unknown";
        }

        @Override
        public String getName() {
            return "unknown";
        }

        @Override
        public int getScore() {
            return 0;
        }
    };
    private final String id;

    public RoomUnknownPlayer(String id) {
        this.id = id;
    }

    private Optional<User> getPlayer() {
        return UserHolder.get(id);
    }

    @Override
    public String getId() {
        return getPlayer().map(User::getId).orElse("Unknown");
    }

    @Override
    public String getName() {
        return getPlayer().map(user ->
                user.getProfile().getNick()).orElse("Unknown");
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public boolean isReady() {
        return getPlayer().filter(user -> user.getGame().isReady()).isPresent();
    }

    @Override
    protected void setReady0(boolean ready) {
        getPlayer().ifPresent(user -> user.setReady(ready));
    }
}
