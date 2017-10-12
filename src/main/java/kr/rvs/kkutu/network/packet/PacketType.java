package kr.rvs.kkutu.network.packet;

/**
 * Created by Junhyeong Lim on 2017-10-12.
 */
public enum PacketType {
    IN_CHAT("chat"),
    IN_ROOM("room"),
    IN_WELCOME("welcome"),
    IN_DISCONNECT("disconn"),
    IN_CONNECT("conn"),

    OUT_CHAT("talk"),

    EMPTY("empty"),
    ;
    private final String name;

    public String internalName() {
        return name;
    }

    PacketType(String name) {
        this.name = name;
    }
}
