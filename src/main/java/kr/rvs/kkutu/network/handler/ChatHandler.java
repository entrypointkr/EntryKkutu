package kr.rvs.kkutu.network.handler;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kr.rvs.kkutu.gui.Chatable;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.impl.ChatOutPacket;
import kr.rvs.kkutu.network.packet.impl.in.ChatInPacket;
import kr.rvs.kkutu.network.packet.impl.in.YellPacket;

public class ChatHandler implements PacketHandler, EventHandler<KeyEvent> {
    private final Chatable chatable;
    private final PacketManager manager;

    public ChatHandler(Chatable chatable, PacketManager manager) {
        this.chatable = chatable;
        this.manager = manager;
    }

    @Override
    public void handle(Packet packet) {
        if (packet instanceof ChatInPacket) {
            ChatInPacket chatPacket = ((ChatInPacket) packet);
            chatable.chat(chatPacket.getProfile(), chatPacket.getMessage());
        } else if (packet instanceof YellPacket) {
            YellPacket yellPacket = ((YellPacket) packet);
            chatable.chat("[공지] " + yellPacket.getValue());
        }
    }

    @Override
    public void handle(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            manager.sendPacket(new ChatOutPacket(textField.getText()));
            textField.setText("");
        }
    }
}
