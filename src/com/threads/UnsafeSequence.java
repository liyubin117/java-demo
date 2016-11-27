package com.threads;

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
