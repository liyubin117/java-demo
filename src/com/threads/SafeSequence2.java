package com.threads;
import java.util.concurrent.atomic.AtomicLong;

import com.sql.*;

public class SafeSequence2 implements Runnable{
	private AtomicLong i=new AtomicLong(0);
	
	
	@Override
	public void run(){
		System.out.println(i.incrementAndGet());
	}
	
	public static void main(String[] args){
		SafeSequence2 us=new SafeSequence2();
		Thread s1=new Thread(us);
		Thread s2=new Thread(us);
		s1.start();
		s2.start();
		
	}
}
