package kr.rvs.kkutu.util;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Junhyeong Lim on 2017-10-07.
 */
public class Static {
    public static void log(Level level, String message) {
        Logger.getAnonymousLogger().log(level, message);
    }

    public static void log(Throwable throwable) {
        Logger.getAnonymousLogger().log(Level.WARNING, throwable, throwable::getMessage);
    }

    public static <T> Optional<T> cast(Object object, Class<T> aClass) {
        Optional<T> ret = Optional.empty();
        try {
            if (aClass.isInstance(object))
                ret = Optional.of((T) object);
        } catch (Exception ex) {
            // Ignore
        }
        return ret;
    }
}
