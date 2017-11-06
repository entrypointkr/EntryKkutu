package kr.rvs.kkutu.holder;

import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gui.LobbyController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class UserHolder {
    private static final UserHolder INSTANCE = new UserHolder();
    private LobbyController controller;
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public static UserHolder getInst() {
        return INSTANCE;
    }

    private void refresh() {
        controller.updateUsers(userMap);
    }

    public void add(User user) {
        userMap.put(user.id(), user);
        refresh();
    }

    public void add(Map<String, User> map) {
        this.userMap.putAll(map);
        refresh();
    }

    public User remove(String key) {
        User old = userMap.remove(key);
        refresh();
        return old;
    }

    public Optional<User> get(String key) {
        return Optional.ofNullable(userMap.get(key));
    }

    public List<User> getUsers() {
        return new ArrayList<>(userMap.values());
    }

    public void setController(LobbyController controller) {
        this.controller = controller;
    }
}
