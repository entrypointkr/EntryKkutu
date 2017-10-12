package kr.rvs.kkutu.holder;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.TitledPane;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.util.Servers;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class UserHolder {
    private final Servers server;
    private final TitledPane titledPane;
    private final ListView<String> userView;
    private final Map<String, User> userMap = new ConcurrentHashMap<>();

    public UserHolder(Servers server, TitledPane titledPane, ListView<String> userView) {
        this.server = server;
        this.titledPane = titledPane;
        this.userView = userView;
    }

    private void refresh() {
        ObservableList<String> items = userView.getItems();

        Platform.runLater(() -> {
            items.clear();
            for (User user : userMap.values()) {
                items.add(user.getProfile().getName());
            }
            userView.refresh();
            titledPane.setText(String.format("%s (%d명)", server.getName(), items.size()));
        });
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

    public Collection<User> getUsers() {
        return userMap.values();
    }
}
