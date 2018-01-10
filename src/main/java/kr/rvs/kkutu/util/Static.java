package kr.rvs.kkutu.util;

import javafx.application.Platform;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Static {
    public static void log(Level level, String message) {
        Logger.getAnonymousLogger().log(level, message);
    }

    public static void log(Throwable throwable) {
        Logger.getAnonymousLogger().log(Level.WARNING, throwable, throwable::getMessage);
    }

    public static void runOnMain(Runnable runnable) {
        if (Platform.isFxApplicationThread()) {
            runnable.run();
        } else {
            Platform.runLater(runnable);
        }
    }

    private Static() {
    }
}
