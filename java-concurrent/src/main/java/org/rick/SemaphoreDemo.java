package org.rick;
import java.util.concurrent.Semaphore;
/**
 * 信号量
 * 可用于限流
 */
public class SemaphoreDemo {
    public static class ConcurrentLimitException extends RuntimeException {
        private static final long serialVersionUID = 1L;
    }

    private static final int MAX_PERMITS = 2;
    private Semaphore permits = new Semaphore(MAX_PERMITS, true);
    
    public static void main(String[] args) throws InterruptedException {
    	Semaphore permits = new Semaphore(1);
    	permits.acquire();
//    	permits.acquire();
    	System.out.println("acquired 1");
    	permits.release();
        Thread.sleep(500);

        SemaphoreDemo sd = new SemaphoreDemo();
        sd.login("liyubin","123");
        sd.login("li","123");
        sd.login("123","123");
    }

    public boolean login(String name, String password) {
        if (!permits.tryAcquire()) {
            System.out.println("同时登录用户数超过限制");
            throw new ConcurrentLimitException();
        }
        // ..其他验证
        System.out.println(name+"已登陆");
        return true;
    }

    public void logout(String name) {
        System.out.println(name+"已登出");
        permits.release();
    }
}