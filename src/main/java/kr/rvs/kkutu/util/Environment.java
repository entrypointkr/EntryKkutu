package kr.rvs.kkutu.util;

/**
 * Created by Junhyeong Lim on 2017-10-19.
 */
public class Environment {
    private static Server server;

    public static Server getServer() {
        return server;
    }

    public static void setServer(Server server) {
        Environment.server = server;
    }
}
