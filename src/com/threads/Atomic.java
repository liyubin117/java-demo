package com.threads;

import java.util.concurrent.atomic.AtomicInteger;

public class Atomic {

	public static void main(String[] args) {
		Counter counter=new Counter(10000);
		counter.oper();
	}

}

class Counter{
	private static AtomicInteger i=new AtomicInteger(0);
	private int max;
	
	public Counter(int max){
		this.max=max;
	}
	
	static class Count extends Thread{
		@Override
		public void run(){
			//类似于++i
			i.incrementAndGet();
		}
	}
	
	public void oper(){
		Thread[] s=new Thread[max];
		for(int num=0;num<max;num++){
			s[num]=new Count();
			s[num].start();
		}
		for(int num=0;num<max;num++){
			try {
				s[num].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(i.get());
	}
	
}