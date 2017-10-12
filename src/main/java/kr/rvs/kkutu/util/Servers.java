package kr.rvs.kkutu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public enum Servers {
    KKUTU_IO("ws://kkutu.io", 21000, "끄투리오"),
    KKUTU_COKR("ws://ws.kkutu.co.kr", 8080, "끄투코리아")
    ;
    private final String url;
    private final int port;
    private final String name;

    private static String getToken(String name) {
        String token = null;
        File file = new File(name.replace(' ', '_').trim() + ".token");
        try (BufferedReader reader = file.isFile() ? new BufferedReader(new FileReader(file)) : new BufferedReader(new InputStreamReader(System.in))) {
            token = reader.readLine();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                writer.write(token);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return token;
    }

    Servers(String url, int port, String name) {
        this.url = url;
        this.port = port;
        this.name = name;
    }

    public URI toURI() {
        try {
            return new URI(String.format("%s:%d/%s", url, port, getToken(name)));
        } catch (URISyntaxException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getUrl() {
        return url;
    }

    public int getPort() {
        return port;
    }

    public String getName() {
        return name;
    }
}
