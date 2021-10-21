package org.rick;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

import model.Account;

//显式锁
public class ReentrantLockDemo {

    public static void main(String[] args) throws InterruptedException {
        //用显式锁实现计数
        CounterLock i = new CounterLock();
        i.oper(1000);
        //非阻塞获取锁tryLock可以避免死锁发生
        AccountMgr.simulate(2, 3, true);

        Thread.sleep(1500);
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        System.out.println("是否被锁住：" + lock.isLocked());
        System.out.println("锁是否被当前线程持有：" + lock.isHeldByCurrentThread());
        System.out.println("锁等待策略是否公平：" + lock.isFair());
        System.out.println("是否有线程在等待该锁：" + lock.hasQueuedThreads());
        System.out.println("在等待该锁的线程数：" + lock.getQueueLength());

        //ReentrantLock是用LockSupport和CAS实现的
        Thread t = new Thread() {
            public void run() {
                //线程t调用park后，会放弃CPU进入WAITING状态，main线程调用unpark唤醒。
                LockSupport.park();
                System.out.println("thread t exit");
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("thread t interrupted");
                }
            }
        };
        t.start();
        Thread.sleep(1000);
        System.out.println("当前线程sleep");
        //park是响应中断的
        t.interrupt();
        System.out.println("中断线程t");
//		    LockSupport.unpark(t);

    }

}

class CounterLock {
    private final Lock lock = new ReentrantLock();
    private volatile int count;

    class CountTask extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                count++;
            } finally {
                lock.unlock();
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void oper(int max) {
        Thread[] s = new Thread[max];
        CounterLock i = new CounterLock();
        for (int num = 0; num < max; num++) {
            s[num] = i.new CountTask();
            s[num].start();
        }
        for (int num = 0; num < max; num++) {
            try {
                s[num].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(i.getCount());
    }
}


class AccountMgr {
    public static class NoEnoughMoneyException extends Exception {
        private static final long serialVersionUID = -5477858820249336182L;
    }

    //阻塞式获取锁
    public static void transfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {
        from.lock();
        try {
            to.lock();
            try {
                if (from.getMoney() >= money) {
                    from.reduce(money);
                    to.add(money);
                } else {
                    throw new NoEnoughMoneyException();
                }
            } finally {
                to.unlock();
            }
        } finally {
            from.unlock();
        }
    }

    //非阻塞式获取锁
    public static boolean tryTransfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {
        if (from.tryLock()) {
            try {
                if (to.tryLock()) {
                    try {
                        if (from.getMoney() >= money) {
                            from.reduce(money);
                            to.add(money);
                        } else {
                            throw new NoEnoughMoneyException();
                        }
                        return true;
                    } finally {
                        to.unlock();
                    }
                }
            } finally {
                from.unlock();
            }
        }
        return false;
    }

    public static void safeTransfer(Account from, Account to, double money)
            throws NoEnoughMoneyException {
        boolean success = false;
        do {
            success = tryTransfer(from, to, money);
            if (!success) {
                Thread.yield();
            }
        } while (!success);
    }

    //若isSafe为true，则调用不发生死锁的过程。
    public static void simulate(final int accountNum, final int threadNum, final boolean isSafe) {
//        final int accountNum = 10;
        final Account[] accounts = new Account[accountNum];
        final Random rnd = new Random();
        for (int i = 0; i < accountNum; i++) {
            accounts[i] = new Account(rnd.nextInt(10000));
        }
        System.out.println(isSafe ? "不发生死锁" : "可能发生死锁");

//        int threadNum = 100;
        Thread[] threads = new Thread[threadNum];
        for (int i = 0; i < threadNum; i++) {
            threads[i] = new Thread() {
                public void run() {
                    int loopNum = 100;
                    for (int k = 0; k < loopNum; k++) {
                        int i = rnd.nextInt(accountNum);
                        int j = rnd.nextInt(accountNum);
                        int money = rnd.nextInt(10);
                        if (i != j) {
                            try {
                                if (!isSafe) {
                                    transfer(accounts[i], accounts[j], money);
                                } else {
                                    safeTransfer(accounts[i], accounts[j], money);
                                }
                            } catch (NoEnoughMoneyException e) {
                            }
                        }
                    }
                }
            };
            threads[i].start();
        }
    }

}