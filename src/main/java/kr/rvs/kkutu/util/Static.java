package kr.rvs.kkutu.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Static {
    public static void log(Level level, String message) {
        Logger.getAnonymousLogger().log(level, message);
    }

    public static void log(Throwable throwable) {
        Logger.getAnonymousLogger().log(Level.WARNING, throwable, throwable::getMessage);
    }

    public static String readContents(BufferedReader reader) throws IOException {
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();
        return response.toString();
    }

    public static String readContents(File file) throws IOException {
        return readContents(new BufferedReader(new FileReader(file)));
    }

    private Static() {
    }
}
