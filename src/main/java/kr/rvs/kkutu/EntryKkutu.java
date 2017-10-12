package kr.rvs.kkutu;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class EntryKkutu extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        int minWidth = 700;
        int minHeight = 450;
        Parent root = FXMLLoader.load(getClass().getResource("/Lobby.fxml"));
        primaryStage.setTitle("EntryKkutu");
        primaryStage.setScene(new Scene(root, minWidth, minHeight));
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.setMinWidth(minWidth);
        primaryStage.setMinHeight(minHeight);
        primaryStage.show();
    }
}
