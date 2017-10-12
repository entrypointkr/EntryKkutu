package kr.rvs.kkutu.network;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakerFactory;
import io.netty.handler.codec.http.websocketx.WebSocketVersion;
import kr.rvs.kkutu.gui.LobbyController;
import kr.rvs.kkutu.holder.RoomHolder;
import kr.rvs.kkutu.holder.UserHolder;
import kr.rvs.kkutu.network.handler.ChatHandler;
import kr.rvs.kkutu.network.handler.UpdateHandler;
import kr.rvs.kkutu.util.Servers;

import java.net.URI;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class WebSocketClient extends Connection {
    private final Servers server;
    private final PacketManager manager;

    public WebSocketClient(Servers server, PacketManager manager) {
        super(server.getPort());
        this.server = server;
        this.manager = manager;
    }

    @Override
    public void run0() throws Exception {
        URI uri = server.toURI();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            final WebSocketClientHandler handler = new WebSocketClientHandler(
                    WebSocketClientHandshakerFactory.newHandshaker(
                            uri,
                            WebSocketVersion.V13,
                            null,
                            false,
                            new DefaultHttpHeaders(),
                            Integer.MAX_VALUE
                    ),
                    manager
            );

            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipe = ch.pipeline();
                            pipe.addLast(
                                    new HttpClientCodec(),
                                    new HttpObjectAggregator(Integer.MAX_VALUE),
                                    new WebSocketMonitor(),
                                    handler
                            );
                        }
                    });

            Channel ch = bs.connect(uri.getHost(), uri.getPort()).sync().channel();
            manager.setChannel(ch);
            handler.handshakeFuture().sync();

            ch.closeFuture().sync();
        } finally {
            group.shutdownGracefully();
        }
    }
}
