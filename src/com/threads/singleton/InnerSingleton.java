package com.threads.singleton;
//内部静态类实现单例模式，也适用于多线程情景
public class InnerSingleton {
    private static class Singleton{
        private static Singleton single = new Singleton();
    }

    public static Singleton getInstance(){
        return Singleton.single;
    }

    public static void main(String[] args){
        Thread s1 = new Thread(){
            @Override
            public void run(){
                System.out.println(InnerSingleton.getInstance().hashCode());
            }
        };
        Thread s2 = new Thread(){
            @Override
            public void run(){
                System.out.println(InnerSingleton.getInstance().hashCode());
            }
        };
        Thread s3 = new Thread(){
            @Override
            public void run(){
                System.out.println(InnerSingleton.getInstance().hashCode());
            }
        };
        s1.start();
        s2.start();
        s3.start();
    }
}
