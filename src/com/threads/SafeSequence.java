package com.threads;
import com.sql.*;

public class SafeSequence implements Runnable{
	private int i=0;
	
	public synchronized int getNext(){
		return ++i;
	}
	
	@Override
	public void run(){
		System.out.println(getNext());
	}
	
	public static void main(String[] args){
		SafeSequence us=new SafeSequence();
		Thread s1=new Thread(us);
		Thread s2=new Thread(us);
		s1.start();
		s2.start();
		
	}
}
