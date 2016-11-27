package com.threads;

public class TestRunnable implements Runnable{
	private int i;
	
	@Override
	public void run() {
		synchronized(this){
			for(i=0;i<100;i++){
				System.out.println(Thread.currentThread().getName()+" is working:"+i);
			}
		}

		
	}

	public static void main(String[] args) throws InterruptedException {
		TestRunnable m1=new TestRunnable();
		Thread s1=new Thread(m1,"s1");
		
//		MyThread2 m2=new MyThread2();
		Thread s2=new Thread(m1,"s2");

		Thread.currentThread().setPriority(10);
		s1.setPriority(1);
		s2.setPriority(2);
		Thread.yield();
		
		s1.start();
		s2.start();

		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" is working:"+i);
		}
		
//		Thread.currentThread().join(1000);
//		Thread.sleep(1000);

		
		
		
//		System.out.println(s1.isDaemon());
	}

	

}
