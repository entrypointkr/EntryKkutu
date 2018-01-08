package kr.rvs.kkutu.util;

public class Validate {
    public static void isTrue(boolean condition, String message) {
        if (!condition) {
            throw new IllegalStateException(message);
        }
    }

    public static void isTrue(boolean condition) {
        isTrue(condition, "Condition is not true.");
    }

    private Validate() {
    }
}
