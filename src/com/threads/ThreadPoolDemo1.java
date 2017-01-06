package com.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolDemo1 {

	public static void main(String[] args) {
		ExecutorService pool=Executors.newFixedThreadPool(2);
		ThreadDemo m=new ThreadDemo();
		Thread s1=new Thread(m,"s1");
		Thread s2=new Thread(m,"s2");
		Thread s3=new Thread(m,"s3");
		pool.submit(s1);
		pool.submit(s2);
		pool.submit(s3);
		//线程池设置大小为2，提交3个线程后，虽然只显示有2个线程执行了，但其实3个线程的任务都被执行完，只是一个执行完后另一个开始执行
		pool.shutdown();
	}

}
