package kr.rvs.kkutu.network;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import kr.rvs.kkutu.network.packet.Packet;
import kr.rvs.kkutu.network.packet.WritablePacket;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PacketManager extends SimpleChannelInboundHandler<Packet> {
    private static final PacketManager MANAGER = new PacketManager();
    private Channel channel;
    private final List<PacketHandler> handlers = new ArrayList<>();

    public static PacketManager get() {
        return MANAGER;
    }

    private PacketManager() {
    }

    public void init(Channel channel) {
        this.channel = channel;
    }

    public void sendPacket(WritablePacket packet) {
        channel.writeAndFlush(packet);
    }

    public void addHandler(PacketHandler... handlers) {
        this.handlers.addAll(Arrays.asList(handlers));
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        handlers.forEach(handler -> handler.handle(msg));
    }
}
