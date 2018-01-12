package kr.rvs.kkutu.network.handler;

import kr.rvs.kkutu.game.User;
import kr.rvs.kkutu.network.PacketHandler;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.in.RoomJoinPacket;
import kr.rvs.kkutu.network.packet.out.KickPacket;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class KickHandler implements PacketHandler {
    private final Set<String> whitelist = new HashSet<>();

    public KickHandler(String... nicks) {
        whitelist.addAll(Arrays.asList(nicks));
    }

    private boolean isNotWhitelist(User user) {
        return !whitelist.contains(user.getProfile().getNick());
    }

    private void kick(PacketManager manager, User user) {
        manager.sendPacket(new KickPacket(false, user.getId()));
    }

    @Override
    public void handle(PacketManager manager, Packet packet) {
        if (packet instanceof RoomJoinPacket) {
            RoomJoinPacket joinPacket = ((RoomJoinPacket) packet);
            User user = joinPacket.getUser();
            if (isNotWhitelist(user)) {
                kick(manager, user);
            }
        }
    }
}
