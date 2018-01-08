package kr.rvs.kkutu.gui;


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class InstantTextCleaner implements ChangeListener<Boolean> {
    private final TextField textField;

    public InstantTextCleaner(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
        textField.setText("");
        textField.focusedProperty().removeListener(this);
    }
}
