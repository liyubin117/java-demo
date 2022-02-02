package org.rick.async;

import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.CompletableFuture;
import static org.rick.Utils.printThread;
import static org.rick.Utils.sleepMillis;

public class CompletableFutureDemo {
    @Before
    public void setBefore() {
        printThread("小明进饭店了");
        printThread("小明点了一份炒饭");
        printThread("小明在玩游戏");
    }

    @Test
    public void test1() {
        CompletableFuture<String> cook = CompletableFuture.supplyAsync(() -> {
            printThread("厨师开始做饭");
            sleepMillis(2000);
            printThread("厨师开始打饭");
            sleepMillis(1000);
            return "饭送上来了";
        });
        printThread(cook.join() + "，小明开吃");
    }

    @Test
    public void test2() {
        CompletableFuture<String> cook = CompletableFuture.supplyAsync(() -> {
            printThread("厨师开始做饭");
            sleepMillis(2000);
            return "饭做好了";
        }).thenCompose(in -> CompletableFuture.supplyAsync(() -> {
            printThread("服务员开始打饭");
            sleepMillis(1000);
            return "饭送上来了";
        }));
        printThread(cook.join() + "，小明开吃");
    }

    @Test
    public void test3() {
        CompletableFuture<String> cook = CompletableFuture.supplyAsync(() -> {
            printThread("厨师开始做饭");
            sleepMillis(2000);
            return "饭做好了";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            printThread("服务员开始煮米");
            sleepMillis(1000);
            return "米煮好了";
        }), (A, B) -> {
            printThread("服务员开始打饭");
            sleepMillis(1000);
            return A + "," + B + "," + "饭送上来了";
        });
        printThread(cook.join() + "，小明开吃");
    }

    @Test
    public void test4() {
        CompletableFuture<String> run = CompletableFuture.supplyAsync(() -> {
            sleepMillis(2000);
            printThread("1号公交到了");
            return "1号公交";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            sleepMillis(1000);
            printThread("2号公交到了");
            return "2号公交";
        }), (bus) -> bus);
        printThread(String.format("小明坐上%s", run.join()));
    }

    @Test
    public void test5() {
        CompletableFuture<String> run = CompletableFuture.supplyAsync(() -> {
            sleepMillis(2000);
            printThread("1号公交到了");
            return "1号";
        }).applyToEither(CompletableFuture.supplyAsync(() -> {
            sleepMillis(1000);
            printThread("2号公交到了");
            return "2号";
        }), (bus) -> {
            if (bus.equals("2号")) {
                throw new RuntimeException("车熄火了...");
            }
            return bus;
        }).exceptionally((exception) -> {
            printThread(String.format("由于%s，打出租车", exception.getMessage()));
            return "出租车";
        });
        printThread(String.format("小明坐上%s", run.join()));
    }
}
