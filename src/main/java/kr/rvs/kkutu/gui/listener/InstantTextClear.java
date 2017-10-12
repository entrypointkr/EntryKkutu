package kr.rvs.kkutu.gui.listener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class InstantTextClear implements ChangeListener<Boolean> {
    private final TextField textField;

    public InstantTextClear(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        textField.setText("");
        textField.focusedProperty().removeListener(this);
    }
}
