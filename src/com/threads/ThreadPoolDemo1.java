package com.threads;

import javax.annotation.PreDestroy;
import java.util.Date;
import java.util.concurrent.*;

public class ThreadPoolDemo1 {

	private static ExecutorService pool;

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		pool=Executors.newFixedThreadPool(2);
		ThreadDemo m=new ThreadDemo();
		Thread s1=new Thread(m,"s1");
		Thread s2=new Thread(m,"s2");
		Thread s3=new Thread(m,"s3");
		System.out.println("submit task: "+new Date());
		pool.submit(s1);
		pool.submit(s2);
		pool.submit(s3);
//		pool.submit(new Runnable() {
//			@Override
//			public void run() {
//				test();
//			}
//		});

		System.out.println("hello");

		Future<String> future = pool.submit(new Callable() {
			@Override
			public Object call() throws Exception {
//				while (!Thread.interrupted()){
//				}
				TimeUnit.SECONDS.sleep(2);
				return "success:" + new Date();
			}
		});
		//线程池设置大小为2，提交3个线程后，虽然只显示有2个线程执行了，但其实3个线程的任务都被执行完，只是一个执行完后另一个开始执行
		System.out.println(future.get());

		pool.shutdown();

	}

	public static void test(){
		while (!Thread.interrupted()){

		}
	}

}
