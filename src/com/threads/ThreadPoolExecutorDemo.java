package com.threads;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorDemo {

	public static void main(String[] args) {
		ExecutorService executor=null;
		//获得当前系统的可用处理器数量
		//int minPoolSize=Runtime.getRuntime().availableProcessors();
		int minPoolSize=2;
		int maxPoolSize=4;
		//有界阻塞队列
		BlockingQueue<Runnable> queue=new ArrayBlockingQueue<Runnable>(20);
		//无界阻塞队列。使用无参构造函数构造出来的是无界的，但可传入容量参数使之变为有界
//		BlockingQueue<Runnable> queue=new LinkedBlockingQueue<Runnable>();
		
		//可用来配置创建的线程名等，默认是pool-poolId-thread-threadId
		ThreadFactory factory=new ThreadFactory(){
			@Override
			public Thread newThread(Runnable r) {
				 Thread s=new Thread(r);
				 s.setName("线程池");
				 return s;
			}
		};
		executor=new ThreadPoolExecutor(
				minPoolSize,
				maxPoolSize,1L,
				TimeUnit.SECONDS,
				queue,
//				factory,  //自定义的ThreadFactory
				Executors.defaultThreadFactory(),  //默认的ThreadFactory
				new ThreadPoolExecutor.CallerRunsPolicy()  //多出来的任务由调用线程执行，本例中是main线程
				);
		
		ThreadDemo t=new ThreadDemo();
		final int max=50;
		Thread[] ss=new  Thread[max];
		for(int i=0;i<max;i++){
			ss[i]=new Thread(t);
		}
		
		for(int i=0;i<max;i++){
			executor.execute(ss[i]);
		}
		
		executor.shutdown();
		
	}

}
