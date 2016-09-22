package com.threads;

public class MyThread2 implements Runnable{
	private int i;
	
	@Override
	public void run() {
		for(i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" is working:"+i);
		}
		
	}

	public static void main(String[] args) throws InterruptedException {
		MyThread2 m1=new MyThread2();
		Thread s1=new Thread(m1,"s1");
		
		MyThread2 m2=new MyThread2();
		Thread s2=new Thread(m2,"s2");
		
		s1.start();
		s2.start();

		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" is working:"+i);
		}
		
//		Thread.currentThread().join(1000);
//		Thread.sleep(1000);
		Thread.currentThread().setPriority(10);
		s1.setPriority(1);
		s2.setPriority(2);
		Thread.yield();
		
		
		
//		System.out.println(s1.isDaemon());
	}

	

}
