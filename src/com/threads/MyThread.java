package com.threads;

public class MyThread extends Thread{
	
	private int i=0;
	
	@Override
	public void run(){
		for(int i=0;i<100;i++){
			System.out.println(getName()+" working..."+i);
			if(i==20){
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		MyThread t1=new MyThread();
		t1.setName("low");
		
		MyThread t2=new MyThread();
		t2.setName("t2");
		
		
		Thread.currentThread().setName("high");
		
		t1.setPriority(Thread.MIN_PRIORITY);
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		t1.start();
//		t2.start();
		
//		Thread.sleep(5000);
		
		
//		t2.join(1000);
		
//		Thread.yield();
		
//		System.out.println("-----"+t1.isAlive());
//		System.out.println("-----"+t2.isAlive());
		for(int i=0;i<100;i++){
			System.out.println(Thread.currentThread().getName()+" working..."+i);
		}
		

	}

}
