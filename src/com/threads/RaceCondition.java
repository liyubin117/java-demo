package com.threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 竞态条件
 * 当多个线程访问和操作同一个对象时，最终执行结果与执行时序有关，可能正确也可能不正确
 * ++i : i  i+1  i=i+1 三步
 * 解决方法：
 * 	•使用synchronized关键字
 *	•使用显式锁
 *	•使用原子变量
 */
public class RaceCondition implements Runnable{
	private int num=0;
		
	public int getNum(){
		return this.num;
	}
	
	public void setNum(int i){
		this.num=i;
	}
	
	public void incrNum(){
		++num;
	}
	
	@Override
	public void run(){
		incrNum();
	}
	
	public static void main(String[] args) throws InterruptedException{
		RaceCondition us=new RaceCondition();
		//统计值与出现次数的映射
		Map<Integer,Integer> map=new HashMap<>();
		
		Thread[] s=null;
		
		for(int m=0;m<300;m++){
			s=new Thread[1000];
			for(int i=0;i<1000;i++){
				s[i]=new Thread(us);
				s[i].start();
			}
			for(int i=0;i<1000;i++){
				s[i].join();
			}
			
			//统计
			int num=us.getNum();
			if(map.get(num) == null){
				map.put(num, 1);
			}else{
				map.put(num, map.get(num)+1);
			}
			if(m!=299){
				us.setNum(0);
			}
		}

		
		//若是单线程，结果肯定是1000，但多线程的竞态条件下，结果可能小于1000
		System.out.println(us.getNum());
		System.out.println(map.toString());
	}
}
