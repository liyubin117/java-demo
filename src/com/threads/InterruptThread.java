package com.threads;

//线程对中断的反应
public class InterruptThread {

	public static void main(String[] args) throws InterruptedException {
		Thread s=new InterruptRunnable();
		s.start();
		s.interrupt();
		Thread.sleep(500);
		
		
		s=new InterruptTimedWaiting1();
		s.start();
		s.interrupt();
		Thread.sleep(500);
		
		
		s=new InterruptTimedWaiting();
		s.start();
		s.interrupt();
		Thread.sleep(500);
		
		InterruptBlocked.test();
		
		InterruptNotAlive.test();
		
	}

}

class InterruptRunnable extends Thread{
	@Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("InterruptRunnable thread is working...");
        }
        System.out.println("InterruptRunnable thread done...");
    }
}

class InterruptTimedWaiting1 extends Thread{
	@Override
    public void run() {
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			//此处返回false，因为抛出异常后，中断标志位会被清空，而不是被设置
	        System.out.println("InterruptWating thread done... isInterrupted:"+Thread.currentThread().isInterrupted());
		} catch(Exception e){
			System.out.println("InterruptWating thread done... isInterrupted:"+Thread.currentThread().isInterrupted());
		}
    }
}
class InterruptTimedWaiting extends Thread{
	@Override
    public void run() {
        try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			//此处返回true，因为重新设置中断
			Thread.currentThread().interrupt();
	        System.out.println("InterruptTimedWaiting thread done... isInterrupted:"+Thread.currentThread().isInterrupted());
		}
    }
}

class InterruptBlocked extends Thread {
	private static Object lock=new Object();
	
    @Override
    public void run() {
        synchronized (lock) {
            while (!Thread.currentThread().isInterrupted()) {
            }
        }
        System.out.println(Thread.currentThread().getName()+" thread done...");
    }
    
    public static void test() throws InterruptedException{
    	synchronized(lock){
    		Thread s=new InterruptBlocked();
    		s.start();
    		s.interrupt();
    		System.out.println("Blocked thread isInterrupted:"+s.isInterrupted());
    //join加上则将一直执行，不加上则会结束。
    //因为main线程取得了lock锁后，创建s线程，s线程等待lock锁而进入锁等待队列，变成blocked状态，此时中断只会设置中断标志为true，而不会真正停止s线程
    //若有join，则main会一直等待s执行完后才结束，从而无法结束；若无join，则main不再等待s结束，main释放锁后，s获得锁并检测到中断，从而退出
    //根本原因：使用synchronized关键字获取锁的过程中不响应中断请求，这是synchronized的局限性。使用显式锁则支持以响应中断的方式获取锁
//    		s.join();
    	}
    }
}

//无任何效果，中断标志位也不会被设置
class InterruptNotAlive extends Thread{
	@Override
	public void run(){
		
	}
	
	public static void test(){
		InterruptNotAlive s=new InterruptNotAlive();
		s.interrupt();
		System.out.println("New thread isInterrupted:"+s.isInterrupted());
		
		try {
			s.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		s.interrupt();
		System.out.println("Terminate thread isInterrupted:"+s.isInterrupted());
		
	}
}

