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
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import kr.rvs.kkutu.network.PacketFactory;
import kr.rvs.kkutu.network.PacketManager;
import kr.rvs.kkutu.util.Static;

import java.net.URI;

public class WebSocket extends Thread {
    private final EventLoopGroup group = new NioEventLoopGroup();
    private final String name;
    private final URI uri;
    private final PacketFactory packetFactory;
    private final PacketManager packetManager;
    private final Runnable closeCallback;
    private final boolean monitor;

    public WebSocket(String name, URI uri, PacketFactory packetFactory, PacketManager packetManager, Runnable closeCallback, boolean monitor) {
        this.name = name;
        this.uri = uri;
        this.packetFactory = packetFactory;
        this.packetManager = packetManager;
        this.closeCallback = closeCallback;
        this.monitor = monitor;
    }

    public WebSocket(String name, URI uri, PacketFactory packetFactory, PacketManager packetManager, Runnable exitCallback) {
        this(name, uri, packetFactory, packetManager, exitCallback, false);
    }

    public WebSocket(String name, URI uri, PacketFactory packetFactory, PacketManager packetManager) {
        this(name, uri, packetFactory, packetManager, () -> {
            // Empty
        });
    }

    @Override
    public void run() {
        try {
            final WebSocketHandshaker handler = new WebSocketHandshaker(
                    WebSocketClientHandshakerFactory.newHandshaker(
                            uri,
                            WebSocketVersion.V13,
                            null,
                            false,
                            new DefaultHttpHeaders(),
                            Integer.MAX_VALUE
                    )
            );

            Bootstrap bs = new Bootstrap();
            bs.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline pipe = ch.pipeline();
                            pipe.addLast(
                                    SslContextBuilder.forClient()
                                            .trustManager(InsecureTrustManagerFactory.INSTANCE).build()
                                            .newHandler(ch.alloc(), uri.getHost(), uri.getPort()),
                                    new HttpClientCodec(),
                                    new HttpObjectAggregator(Integer.MAX_VALUE)
                            );
                            if (monitor) {
                                pipe.addLast(new WebSocketMonitor(name));
                            }
                            pipe.addLast(
                                    new PacketEncoder(),
                                    handler,
                                    new PacketDecoder(packetFactory),
                                    packetManager
                            );
                        }
                    });

            Channel ch = bs.connect(uri.getHost(), uri.getPort()).sync().channel();
            packetManager.init(ch);
            handler.handshakeFuture().sync();

            ch.closeFuture().sync();
            closeCallback.run();
        } catch (Exception ex) {
            Static.log(ex);
        } finally {
            group.shutdownGracefully();
        }
    }
}
