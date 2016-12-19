package com.threads.timer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class TimerTaskDemo1 extends TimerTask{
	private long period;
	public TimerTaskDemo1(long l){
		this.period=l;
	}

	@Override
	public void run() {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
/*		try {
			Thread.sleep(period*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		System.out.println(Thread.currentThread().getName()+"当前系统时间为："+sdf.format(new Date()));
	}
	
}
