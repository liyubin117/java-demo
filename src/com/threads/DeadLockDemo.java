package com.threads;
/**
 * 死锁
 * 运行后aThread和bThread陷入了相互等待。
 * 解决方法：
 * 首先，应该尽量避免在持有一个锁的同时去申请另一个锁，
 * 如果确实需要多个锁，所有代码都应该按照相同的顺序去申请锁
 * 可使用java自带的jstack命令检测死锁
 */
public class DeadLockDemo {
    private static O lockA = new O("lockA");
    private static O lockB = new O("lockB");
    
    static class O{
    	private String desc;
    	public O(String desc){
    		this.desc=desc;
    	}
    	public String getDesc(){
    		return " get lock "+desc;
    	}
    }

    private static void startThreadA() {
        Thread aThread = new Thread() {

            @Override
            public void run() {
                synchronized (lockB) {
                	System.out.println(Thread.currentThread().getName()+lockB.getDesc());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    synchronized (lockA) {
                    	System.out.println(Thread.currentThread().getName()+lockA.getDesc());
                    }
                }
            }
        };
        aThread.setName("aThread");
        aThread.start();
    }

    private static void startThreadB() {
        Thread bThread = new Thread() {
            @Override
            public void run() {
                synchronized (lockA) {
                	System.out.println(Thread.currentThread().getName()+lockA.getDesc());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                    }
                    synchronized (lockB) {
                    	System.out.println(Thread.currentThread().getName()+lockB.getDesc());
                    }
                }
            }
        };
        bThread.setName("bThread");
        bThread.start();
    }

    public static void main(String[] args) {
        startThreadA();
        startThreadB();
    }
}