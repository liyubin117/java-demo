package org.rick;

//synchronized解决竞态条件
public class SafeSequence implements Runnable{
	private int i=0;
	
	public synchronized int getNext(){
		return ++i;
	}
	
	@Override
	public void run(){
		System.out.println(getNext());
	}
	
	public static void main(String[] args) throws InterruptedException{
		SafeSequence us=new SafeSequence();
		while(true){
			Thread s1=new Thread(us);
			Thread s2=new Thread(us);
			s1.start();
			s2.start();
			
			//确保其他线程执行完后，main线程再执行后面的
			s1.join();
			s2.join();
			us.i=0;
			System.out.println("--------");
		}
		
		
	}
}
