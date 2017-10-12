package kr.rvs.kkutu.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * Created by Junhyeong Lim on 2017-10-02.
 */
public enum Servers {
    KKUTU_IO("http://kkutu.io/", "끄투리오"),
    KKUTU_COKR("http://kkutu.co.kr/", "끄투코리아");
    private final String url;
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


    Servers(String url, String name) {
        this.url = url;
        this.name = name;
    }

    public URI toURI(int server) {
        try {
            URL url = getURL(server);
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
                return new URI(parse.substring(tag.length(), parse.indexOf("</span>")));
            }
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
    }

    public URL getURL(int server) {
        try {
            return new URL(url + "?server=" + server);
        } catch (MalformedURLException e) {
            throw new IllegalStateException(e);
        }
    }

    public String getName() {
        return name;
    }
}
