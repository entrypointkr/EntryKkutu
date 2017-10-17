package kr.rvs.kkutu.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by Junhyeong Lim on 2017-10-17.
 */
public class Server {
    private final Servers server;
    private final int index;
    private final String uri;

    public Server(Servers server, int serverIndex) {
        this.server = server;
        this.index = serverIndex;

        try {
            URL url = new URL(server.getStringURL() + "?server=" + serverIndex);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()))) {
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                String tag = "<span id=\"URL\">";
                String parse = response.substring(response.indexOf(tag));
                uri = parse.substring(tag.length(), parse.indexOf("</span>"));
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public Servers getServer() {
        return server;
    }

    public int getIndex() {
        return index;
    }

    public URI getUri(String suffix) {
        return URI.create(uri + suffix);
    }

    public URI getUri(int channel, int id) {
        String portStr = uri.substring(uri.indexOf(':', uri.indexOf(':') + 1) + 1);
        portStr = portStr.substring(0, portStr.indexOf('/'));
        int port = Integer.parseInt(portStr);
        String uri = this.uri.replace(portStr, String.valueOf(port + 416 + (channel - 1)));
        return URI.create(uri + String.format("&%d&%d", channel, id));
    }

    public URI getUri() {
        return getUri("");
    }
}
