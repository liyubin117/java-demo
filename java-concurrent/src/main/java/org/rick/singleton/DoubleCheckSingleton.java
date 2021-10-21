package org.rick.singleton;
//两次检查实现单例模式
public class DoubleCheckSingleton {
    private static DoubleCheckSingleton instance;
    public static DoubleCheckSingleton getInstance(){
        if(instance == null){
            //模拟对象初始化
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (DoubleCheckSingleton.class){
            if(instance == null){
                instance = new DoubleCheckSingleton();
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        Thread s1 = new Thread(){
            @Override
            public void run(){
                System.out.println(DoubleCheckSingleton.getInstance().hashCode());
            }
        };
        Thread s2 = new Thread(){
            @Override
            public void run(){
                System.out.println(DoubleCheckSingleton.getInstance().hashCode());
            }
        };
        Thread s3 = new Thread(){
            @Override
            public void run(){
                System.out.println(DoubleCheckSingleton.getInstance().hashCode());
            }
        };
        s1.start();
        s2.start();
        s3.start();
    }
}
