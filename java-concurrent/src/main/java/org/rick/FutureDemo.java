package org.rick;

import java.util.concurrent.*;

public class FutureDemo implements Callable<String> {
    private String input;

    public FutureDemo(String input){
        this.input = input;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return this.input+" 1秒处理完成";
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String quryStr = "query";
        FutureTask<String> futureTask = new FutureTask<>(new FutureDemo(quryStr));
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future future = executor.submit(futureTask);
        System.out.println("请求完毕");
        System.out.println(futureTask.get());
        executor.shutdown();

        System.out.println(future.get());
    }
}
