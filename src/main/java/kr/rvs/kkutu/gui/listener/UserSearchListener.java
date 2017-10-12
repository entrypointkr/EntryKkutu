package kr.rvs.kkutu.gui.listener;

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
    private final UserHolder userHolder;

    public UserSearchListener(ListView<String> userView, UserHolder userHolder) {
        this.userView = userView;
        this.userHolder = userHolder;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        ObservableList<String> items = userView.getItems();
        items.clear();
        for (User user : userHolder.getUsers()) {
            String name = user.getProfile().getName();
            if (name.toLowerCase().startsWith(newValue.toLowerCase()))
                items.add(name);
        }
        userView.refresh();
    }
}
