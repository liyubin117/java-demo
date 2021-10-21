package org.rick;
public class WaitThread extends Thread {
    private volatile boolean fire = false;

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!fire) {
                    wait();
                }
            }
            System.out.println(Thread.currentThread().getName()+" fired");
        } catch (InterruptedException e) {
        }
    }

    public synchronized void fire() {
    	System.out.println(Thread.currentThread().getName()+" fire");
        this.fire = true;
        notify();
    }

    public static void main(String[] args) throws InterruptedException {
        WaitThread waitThread = new WaitThread();
        waitThread.start();
        Thread.sleep(1000);
        waitThread.fire();
    }
}