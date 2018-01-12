package kr.rvs.kkutu.network.handler;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import kr.rvs.kkutu.game.room.Room;
import kr.rvs.kkutu.gui.RoomController;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.ChatInPacket;

public class RoomChatHandler implements PacketHandler, EventHandler<KeyEvent> {
    private final RoomController controller;

    public RoomChatHandler(RoomController controller) {
        this.controller = controller;
    }

    @Override
    public void handle(PacketManager manager, Packet packet) {
        if (packet instanceof ChatInPacket) {
            ChatInPacket chatPacket = (ChatInPacket) packet;
            controller.chat(chatPacket.getProfile(), chatPacket.getMessage());
        }
    }

    @Override
    public void handle(KeyEvent event) {
        TextField textField = (TextField) event.getSource();
        if (event.getCode() == KeyCode.ENTER) {
            Room room = controller.getRoom();
            room.sendChat(textField.getText());
            textField.setText("");
        }
    }
}
