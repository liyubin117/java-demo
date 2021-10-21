package org.rick;

public class TestThread extends Thread{
	
	private int i=0;
	
	@Override
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(getName()+" working..."+i);
			if(i==20){
				Thread.yield();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		TestThread t1=new TestThread();

		Thread t2 = new Thread(t1, "t2");

		t1.setName("low");
		
		Thread.currentThread().setName("high");
		
		t1.setPriority(Thread.MIN_PRIORITY);
		Thread.currentThread().setPriority(Thread.MAX_PRIORITY);
		
		System.out.println("t1还未start前的状态是："+t1.getState());
		
		t1.start();
//		t2.start();
		
//		Thread.sleep(5000);
		
		
//		t2.join(1000);
		
//		Thread.yield();
		
//		System.out.println("-----"+t1.isAlive());
//		System.out.println("-----"+t2.isAlive());
		
		t1.join();

		for(int i=0;i<10;i++){
			System.out.println(Thread.currentThread().getName()+" working..."+i);
		}
		
		//t1.join();

		
		Thread.currentThread().sleep(1000);
		System.out.println("t1线程是否活着："+t1.isAlive());
		System.out.println("t1线程执行完后的状态是："+t1.getState());
		
		//当所有非daemon线程结束时，程序就会退出。本例daemon线程未输出完就随着程序退出而关闭了
		TestThread daemon=new TestThread();
		daemon.setDaemon(true);
		daemon.setName("daemon");
		daemon.setPriority(MIN_PRIORITY);
		daemon.start();
	}

}
