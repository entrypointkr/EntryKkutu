package kr.rvs.kkutu.gui.listener;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.holder.UserHolder;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class UserSearchListener implements ChangeListener<String> {
    private final ListView<String> userView;

    public UserSearchListener(ListView<String> userView) {
        this.userView = userView;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        Platform.runLater(() -> {
            ObservableList<String> items = userView.getItems();
            items.clear();
            for (User user : UserHolder.getInst().getUsers()) {
                String name = user.getProfile().getName();
                if (name.toLowerCase().startsWith(newValue.toLowerCase()))
                    items.add(name);
            }
            userView.refresh();
        });
    }
}
