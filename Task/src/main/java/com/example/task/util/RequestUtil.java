package com.example.task.util;

public class RequestUtil {
    private static final int TIMEOUT = 5000;

    public static void delayRequest() {
        try {
            Thread.sleep(TIMEOUT);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
