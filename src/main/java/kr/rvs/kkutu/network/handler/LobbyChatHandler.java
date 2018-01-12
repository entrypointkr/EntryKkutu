package kr.rvs.kkutu.network.handler;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.network.LobbyPacketManager;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.out.ChatOutPacket;
import kr.rvs.kkutu.network.packet.in.ChatInPacket;
import kr.rvs.kkutu.network.packet.in.YellPacket;

public class LobbyChatHandler implements PacketHandler, EventHandler<KeyEvent> {
    @Override
    public void handle(PacketManager manager, Packet packet) {
        if (packet instanceof ChatInPacket) {
            ChatInPacket chatPacket = ((ChatInPacket) packet);
            LobbyController.get().chat(chatPacket.getProfile(), chatPacket.getMessage());
        } else if (packet instanceof YellPacket) {
            YellPacket yellPacket = ((YellPacket) packet);
            LobbyController.get().chat("[공지] " + yellPacket.getValue());
        }
    }

    @Override
    public void handle(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            LobbyPacketManager.get().sendPacket(new ChatOutPacket(textField.getText()));
            textField.setText("");
        }
    }
}
