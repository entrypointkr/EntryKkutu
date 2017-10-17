package kr.rvs.kkutu.util;

import kr.rvs.kkutu.factory.game.GameFactory;
import kr.rvs.kkutu.factory.game.KkutuIoGameFactory;
import kr.rvs.kkutu.factory.game.KkutuKoreaGameFactory;
import kr.rvs.kkutu.factory.packet.KkutuIoPacketFactory;
import kr.rvs.kkutu.factory.packet.KkutuKoreaPacketFactory;
import kr.rvs.kkutu.factory.packet.PacketFactory;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public enum Servers {
    KKUTU_IO("http://kkutu.io", "끄투리오", new KkutuIoPacketFactory(), new KkutuIoGameFactory()),
    KKUTU_COKR("http://kkutu.co.kr", "끄투코리아", new KkutuKoreaPacketFactory(), new KkutuKoreaGameFactory());

    private final String url;
    private final String name;
    private final PacketFactory packetFactory;
    private final GameFactory gameFactory;

    Servers(String url, String name, PacketFactory packetFactory, GameFactory gameFactory) {
        this.url = url;
        this.name = name;
        this.packetFactory = packetFactory;
        this.gameFactory = gameFactory;
    }

    public String getName() {
        return name;
    }

    public PacketFactory getPacketFactory() {
        return packetFactory;
    }

    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public String getStringURL() {
        return url;
    }
}
