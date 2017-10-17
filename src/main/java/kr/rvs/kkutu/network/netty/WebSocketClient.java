package kr.rvs.kkutu.network.netty;

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
import kr.rvs.kkutu.network.packet.PacketManager;
import kr.rvs.kkutu.util.Static;

import java.net.URI;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public class WebSocketClient extends Thread {
    private final String name;
    private final URI uri;
    private final PacketManager manager;
    private final EventLoopGroup group = new NioEventLoopGroup();

    public WebSocketClient(String name, URI uri, PacketManager manager) {
        this.name = name;
        this.uri = uri;
        this.manager = manager;
    }

    @Override
    public void run() {
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
                                    new WebSocketMonitor(name),
                                    handler
                            );
                        }
                    });

            Channel ch = bs.connect(uri.getHost(), uri.getPort()).sync().channel();
            manager.setChannel(ch);
            handler.handshakeFuture().sync();

            ch.closeFuture().sync();
        } catch (Exception ex) {
            Static.log(ex);
        } finally {
            group.shutdownGracefully();
        }
    }
}
