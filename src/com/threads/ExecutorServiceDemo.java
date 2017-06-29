package com.threads;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
/**
 * ExecutorService可提交异步任务
 * 两种返回结果方式：invokeAll所有任务都完成再返回、invokeAny任一任务完成即返回
 */
public class ExecutorServiceDemo {
	//定义任务
    static class Task implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            int sleepSeconds = new Random().nextInt(1000);
            System.out.println("asynchronous task running...");
            Thread.sleep(sleepSeconds);
            return sleepSeconds;
        }
    }

    public static void main(String[] args) throws InterruptedException {
    	//提交任务，本例此时已经开始执行
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new Task());

        // 模拟执行其他任务
        System.out.println("other task running...");
        Thread.sleep(1000);

        try {
        	//获得异步任务结果
            System.out.println("asynchronous task result: "+future.get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
        
        //批量提交任务
        //Executors.newFixedThreadPool产生一个线程池，任务可并发执行。
        ExecutorService executor1 = Executors.newFixedThreadPool(10);
        String url1 = "http://www.cnblogs.com/swiftma/p/5396551.html";
        String url2 = "http://www.cnblogs.com/swiftma/p/5399315.html";

        Collection<UrlTitleParser> tasks = Arrays.asList(new UrlTitleParser[] {
                new UrlTitleParser(url1), new UrlTitleParser(url2) });
        try {
        	//invokeAll等待所有任务完成再返回
            List<Future<String>> results = executor1.invokeAll(tasks, 10,
                    TimeUnit.SECONDS);
            for (Future<String> result : results) {
                try {
                    System.out.println(result.get());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
            //invokeAny只要一个任务成功便返回
            String result=executor1.invokeAny(tasks);
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
			e.printStackTrace();
		}

        executor1.shutdown();
        //超时前所有任务都结束了返回true，否则false
        System.out.println(executor1.awaitTermination(1, TimeUnit.MICROSECONDS));
    }
}

class UrlTitleParser implements Callable<String> {
        private String url;

        public UrlTitleParser(String url) {
            this.url = url;
        }

        @Override
        public String call() throws Exception {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("head title");
            if (elements.size() > 0) {
                return elements.get(0).text();
            }
            return null;
        }
}
