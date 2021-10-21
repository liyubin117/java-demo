package org.rick;

//多线程协作的一个应用场景：集合点
//初始线程数作为共享变量，等待条件是未到达的线程数大于0
public class WaitAssemble {

	public static void main(String[] args) throws InterruptedException {
		int num = 10;
        Tourist[] threads = new Tourist[num];
        AssemblePoint ap = new AssemblePoint(num);
        for (int i = 0; i < num; i++) {
            threads[i] = new Tourist(ap);
            threads[i].start();
            
            //此处不可用join，这样的话for循环无法执行，threads[0]在等待ap对象解锁，main在等待threads[0]执行完，而无线程给ap解锁，将一直锁下去
            //threads[i].join();
        }
        
        for(int i=0;i<num;i++){
        	threads[i].join();
        }
        System.out.println("INFO: All threads has arrived!");
	}
	
	
	static class Tourist extends Thread {
        AssemblePoint ap;

        public Tourist(AssemblePoint ap) {
            this.ap = ap;
        }

        @Override
        public void run() {
            try {
                // 模拟先各自独立运行
                Thread.sleep((int) (Math.random() * 1000));

                // 集合
                ap.await();
                System.out.println(Thread.currentThread().getName()+" arrived");
                // ... 集合后执行其他操作
            } catch (InterruptedException e) {
            }
        }
    }

}

//初始线程数作为共享变量
class AssemblePoint {
    private int n;

    public AssemblePoint(int n) {
        this.n = n;
    }

    public synchronized void await() throws InterruptedException {
        if (n > 0) {
            n--;
            if (n == 0) {
                notifyAll();
            } else {
                while (n != 0) {
                    wait();
                }
            }
        }
    }
}

