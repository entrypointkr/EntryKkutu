package kr.rvs.kkutu.network.handler.lobby;

import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.PacketHandler;
import kr.rvs.kkutu.network.packet.PacketHandlers;
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.network.packet.impl.out.ChatOutPacket;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class SpamHandler implements PacketHandler {
    private final Timer timer = new Timer();

    public SpamHandler(PacketManager manager) {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < random.nextInt(5) + 5; i++) {
                    builder.append((char) random.nextInt());
                }
                manager.send(new ChatOutPacket(builder.toString()));
            }
        }, 3000, 3000);
    }

    @Override
    public void handle(PacketHandlers handlers, Packet packet) {

    }
}
