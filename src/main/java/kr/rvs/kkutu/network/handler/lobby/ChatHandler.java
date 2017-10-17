package kr.rvs.kkutu.network.handler.lobby;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.PacketHandlers;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.network.packet.impl.in.ChatInPacket;
import kr.rvs.kkutu.network.packet.impl.in.YellPacket;
import kr.rvs.kkutu.network.packet.impl.out.ChatOutPacket;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public class ChatHandler implements PacketHandler, EventHandler<KeyEvent> {
    private final PacketManager manager;
    private final LobbyController control;

    public ChatHandler(PacketManager manager, LobbyController control) {
        this.manager = manager;
        this.control = control;
        init();
    }

    private void init() {
        control.chatField.setOnKeyPressed(this);
    }

    @Override
    public void handle(PacketHandlers handlers, Packet packet) {
        packet.cast(ChatInPacket.class).ifPresent(chatPacket ->
                control.chat(chatPacket.profile.getName(), chatPacket.content));
        packet.cast(YellPacket.class).ifPresent(yellPacket ->
                control.chat("[SYSTEM]", yellPacket.content));
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
