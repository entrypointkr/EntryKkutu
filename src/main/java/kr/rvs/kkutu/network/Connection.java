package kr.rvs.kkutu.network;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public abstract class Connection extends Thread {
    private final int port;

    public Connection(int port) {
        this.port = port;
    }

    public abstract void run0() throws Exception;

    public int getPort() {
        return port;
    }

    @Override
    public void run() {
        try {
            run0();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
