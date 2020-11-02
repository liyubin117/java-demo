package com.jdksource;

public class VolatileTest {
    private volatile static boolean isRunning=true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            System.out.println(System.currentTimeMillis() + " started");
            while(isRunning){
//                System.out.println("hello");
            }
            System.out.println(System.currentTimeMillis() + " end...");
        }).start();

        Thread.sleep(1000);
        isRunning=false;
    }
}
