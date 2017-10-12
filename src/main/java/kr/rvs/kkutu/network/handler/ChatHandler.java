package kr.rvs.kkutu.network.handler;

import com.google.inject.Inject;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.in.ChatInPacket;
import kr.rvs.kkutu.network.packet.out.ChatOutPacket;
import kr.rvs.kkutu.util.Static;

import java.awt.*;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class ChatHandler implements PacketHandler, EventHandler<KeyEvent> {
    private final PacketManager manager;
    private final LobbyController control;

    @Inject
    private ChatHandler(PacketManager manager, LobbyController control) {
        this.manager = manager;
        this.control = control;
        init();
    }

    private void init() {
        control.chatField.setOnKeyPressed(this);
    }

    @Override
    public void handle(Packet packet) {
        Static.cast(packet, ChatInPacket.class).ifPresent(chatPacket -> {
            control.chat(chatPacket.author, chatPacket.contents);
        });
    }

    @Override
    public void handle(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            manager.send(new ChatOutPacket(textField.getText()));
            textField.setText("");
        }
    }
}
