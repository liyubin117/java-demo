package org.rick;

//多线程协作的两个应用场景：等待开始、等待结束
//子线程等待开始：共享变量是初始值为1的latch对象，主线程countDown，子线程await，等待条件是>0
//主线程等待结束：共享变量是初始值为线程数的latch对象，主线程await，子线程countDown，等待条件是>0
public class WaitBeginEnd {

	public static void main(String[] args) throws InterruptedException {
		WaitBegin wb=new WaitBegin();
		wb.judge();
		Thread.sleep(1000);
		System.out.println();
		
		//使用协作对象，类似于CountDownLatch
		//等待结束
		int workerNum = 10;
	    MyLatch latch = new MyLatch(workerNum);
	    Worker[] workers = new Worker[workerNum];
	    for (int i = 0; i < workerNum; i++) {
	        workers[i] = new Worker(latch);
	        workers[i].start();
	    }
	    latch.await();

	    System.out.println("wait end has fininished!");

	    //等待开始
	    int num = 10;
	    MyLatch latch2 = new MyLatch(1);
        Thread[] racers = new Thread[num];
        for (int i = 0; i < num; i++) {
            racers[i] = new Racer(latch2);
            racers[i].start();
        }
        System.out.println("wait begin....");
        Thread.sleep(3000);
        latch2.countDown();

	}

}

//等待开始
class WaitBegin{
	//协作对象
	static class FireFlag {
	    private volatile boolean fired = false;

	    public synchronized void waitForFire() throws InterruptedException {
	        while (!fired) {
	            wait();
	        }
	    }

	    public synchronized void fire() {
	        this.fired = true;
	        notifyAll();
	    }
	}
	//运动员
	static class Racer extends Thread {
	    FireFlag fireFlag;

	    public Racer(FireFlag fireFlag) {
	        this.fireFlag = fireFlag;
	    }

	    @Override
	    public void run() {
	        try {
	            this.fireFlag.waitForFire();
	            System.out.println("start run "
	                    + Thread.currentThread().getName());
	        } catch (InterruptedException e) {
	        }
	    }
	}
	//新建10个运动员对象并启动线程，当前线程等待1秒后，裁判发出比赛开始指令
	public void judge() throws InterruptedException{
		int num = 10;
	    FireFlag fireFlag = new FireFlag();
	    Thread[] racers = new Thread[num];
	    for (int i = 0; i < num; i++) {
	        racers[i] = new Racer(fireFlag);
	        racers[i].start();
	    }
	    Thread.sleep(1000);
	    fireFlag.fire();
	}
}

//等待结束。join方法就是jdk中的实现，内部调用了wait方法，判断条件是isAlive，当其他线程结束的时候，调用notifyAll

//使用协作对象等待结束或开始。代码更加优雅简洁易懂，JDK中的实现是CountDownLatch
class MyLatch {
  private int count;

  public MyLatch(int count) {
      this.count = count;
  }

  public synchronized void await() throws InterruptedException {
      while (count > 0) {
          wait();
      }
  }

  public synchronized void countDown() {
      count--;
      if (count <= 0) {
          notifyAll();
      }
  }
}
class Worker extends Thread {
    MyLatch latch;

    public Worker(MyLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            // simulate working on task
        	System.out.println(Thread.currentThread().getName()+" is working");
            Thread.sleep((int) (Math.random() * 1000));

            this.latch.countDown();
        } catch (InterruptedException e) {
        }
    }
}
class Racer extends Thread {
    MyLatch latch;

    public Racer(MyLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            this.latch.await();
            System.out.println("start run "
                    + Thread.currentThread().getName());
        } catch (InterruptedException e) {
        }
    }
}
