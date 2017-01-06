package com.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolDemo2 {

	public static void main(String[] args) {
		ExecutorService executor=null;
		//获得当前系统的可用处理器数量
		//int minPoolSize=Runtime.getRuntime().availableProcessors();
		int minPoolSize=2;
		int maxPoolSize=4;
		BlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(20);
		executor=new ThreadPoolExecutor(minPoolSize,maxPoolSize,60L,TimeUnit.SECONDS,queue);
		
		ThreadDemo t=new ThreadDemo();
		Thread s1=new Thread(t);
		Thread s2=new Thread(t);
		Thread s3=new Thread(t);
		Thread s4=new Thread(t);
		Thread s5=new Thread(t);
		Thread s6=new Thread(t);
		
		executor.execute(s1);
		executor.execute(s2);
		executor.execute(s3);
		executor.execute(s4);
		executor.execute(s5);
		executor.execute(s6);
		
		executor.shutdown();
		
	}

}
