package org.rick;
import java.util.concurrent.atomic.AtomicLong;


//原子变量解决竞态条件
public class SafeSequence2 implements Runnable{
	private AtomicLong i=new AtomicLong(0);
	
	
	@Override
	public void run(){
		System.out.println(i.incrementAndGet());
	}
	
	public static void main(String[] args) throws InterruptedException{
		SafeSequence2 us=new SafeSequence2();
		while(true){
			Thread s1=new Thread(us);
			Thread s2=new Thread(us);
			s1.start();
			s2.start();
			
			//确保其他线程执行完后，main线程再执行后面的
			s1.join();
			s2.join();
			us.i.set(0);;
			System.out.println("--------");
		}

	}
}
