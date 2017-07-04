package com.threads;
import java.util.concurrent.Semaphore;
/**
 * 信号量
 *
 */
public class SemaphoreDemo {
    public static class ConcurrentLimitException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    private static final int MAX_PERMITS = 100;
    private Semaphore permits = new Semaphore(MAX_PERMITS, true);
    
    public static void main(String[] args) throws InterruptedException {
    	Semaphore permits = new Semaphore(1);
    	permits.acquire();
    	permits.acquire();
    	System.out.println("acquired");
	}

    public boolean login(String name, String password) {
        if (!permits.tryAcquire()) {
            // 同时登录用户数超过限制
            throw new ConcurrentLimitException();
        }
        // ..其他验证
        return true;
    }

    public void logout(String name) {
        permits.release();
    }
}