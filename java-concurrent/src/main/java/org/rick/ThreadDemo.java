package org.rick;
public class ThreadDemo implements Runnable{
	@Override
	public void run(){
		for(int i=0;i<1;i++){
			System.out.println(Thread.currentThread().getName()+":\t"+i);
		}
	}
}