package kr.rvs.kkutu.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Static {
    public static void log(Level level, String message) {
        Logger.getAnonymousLogger().log(level, message);
    }

    public static void log(Throwable throwable) {
        Logger.getAnonymousLogger().log(Level.WARNING, throwable, throwable::getMessage);
    }

    private Static() {
    }
}
