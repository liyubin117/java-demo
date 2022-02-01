package org.rick;

import java.util.Date;

public class Utils {
    public static void printThread(String content) {
        System.out.println(Thread.currentThread().getName() + "\t" + new Date() + "\t" + content);
    }

    public static void sleepMillis(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
