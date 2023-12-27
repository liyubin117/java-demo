package org.rick.async;

import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CompletableFuture;

import static org.rick.Utils.printThread;
import static org.rick.Utils.sleepMillis;

public class CompletableFutureDemo {
    @Before
    public void setBefore() {
        System.out.println("main线程：" + Thread.currentThread().getId());
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
    public void testThenCompose() {
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
    public void testThenCombine() {
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
    public void testApplyToEither() {
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

    @Test
    public void testHandle() {
        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .handle((s, e) -> {
                    if (e == null) {
                        printThread(s);//未执行
                    } else {
                        printThread(e.getMessage());//java.lang.ArithmeticException: / by zero
                    }
                    return "handle result:" + (s == null ? "500" : s);
                })
                .exceptionally(e -> {
                    printThread("ex:" + e.getMessage()); //未执行
                    return "futureA result: 100";
                });
        printThread(futureB.join());//handle result:500

        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .exceptionally(e -> {
                    printThread("ex:" + e.getMessage()); //java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                })
                .handle((s, e) -> {
                    if (e == null) {
                        printThread(s);//futureA result: 100
                    } else {
                        printThread(e.getMessage());//未执行
                    }
                    return "handle result:" + (s == null ? "500" : s);
                });
        printThread(futureA.join());//handle result:futureA result: 100
    }

    @Test
    public void testWhenComplete() {
        CompletableFuture<String> futureA = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .whenComplete((s, e) -> {
                    if (s != null) {
                        printThread(s);//未执行
                    }
                    if (e == null) {
                        printThread(s);//未执行
                    } else {
                        printThread("ex:" + e.getMessage());//java.lang.ArithmeticException: / by zero
                    }
                })
                .exceptionally(e -> {
                    printThread(e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                    return "futureA result: 100";
                });
        printThread(futureA.join());//futureA result: 100

        CompletableFuture<String> futureB = CompletableFuture.
                supplyAsync(() -> "执行结果:" + (100 / 0))
                .thenApply(s -> "apply result:" + s)
                .exceptionally(e -> {
                    printThread("ex:" + e.getMessage()); //ex:java.lang.ArithmeticException: / by zero
                    return "futureB result: 100";
                })
                .whenComplete((s, e) -> {
                    if (e == null) {
                        printThread(s);//futureA result: 100
                    } else {
                        printThread(e.getMessage());//未执行
                    }
                });
        printThread(futureB.join());//futureA result: 100
    }
}
