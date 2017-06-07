package com.threads;

/**
 * 竞态条件
 * 当多个线程访问和操作同一个对象时，最终执行结果与执行时序有关，可能正确也可能不正确
 * ++i : i  i+1  i=i+1 三步
 * 解决方法：
 * 	•使用synchronized关键字
 *	•使用显式锁
 *	•使用原子变量
 */
public class UnsafeSequence implements Runnable{
	private int i=0;
	
	public int getNext(){
		return ++i;
	}
	
	@Override
	public void run(){
		System.out.println(getNext());
	}
	
	public static void main(String[] args){
		UnsafeSequence us=new UnsafeSequence();
		Thread s1=new Thread(us);
		Thread s2=new Thread(us);
		s1.start();
		s2.start();
	}
}
