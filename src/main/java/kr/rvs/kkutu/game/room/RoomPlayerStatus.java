package kr.rvs.kkutu.game.room;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.awt.*;
import java.util.function.Consumer;

public enum RoomPlayerStatus {

    MASTER(text -> {
        text.setText("방장");
        text.setFill(Color.RED);

    }),
    WAIT(text -> {
        text.setText("대기");
        text.setFill(Color.BLACK);
    }),
    READY(text -> {
        text.setText("준비");
        text.setFill(Color.BLUE);
    });

    private final Consumer<Text> effecter;

    public static RoomPlayerStatus getStatus(Room room, RoomPlayer player) {
        RoomPlayerStatus ready = player.isReady() ? READY : WAIT;
        return room.isMaster(player) ? MASTER : ready;
    }

    RoomPlayerStatus(Consumer<Text> effecter) {
        this.effecter = effecter;
    }

    public void textEffect(Text text) {
        effecter.accept(text);
    }
}
