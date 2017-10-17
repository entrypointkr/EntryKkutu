package kr.rvs.kkutu.holder;

import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.util.Servers;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class UserHolder {
    private final Servers server;
    private final LobbyController controller;
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public UserHolder(Servers server, LobbyController controller) {
        this.server = server;
        this.controller = controller;
    }

    private void refresh() {
        controller.updateUsers(userMap);
    }

    public void add(User user) {
        userMap.put(user.getId(), user);
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

    public Collection<User> getUsers() {
        return userMap.values();
    }
}
