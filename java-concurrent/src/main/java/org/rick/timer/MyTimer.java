package org.rick.timer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;

public class MyTimer {

	public static void main(String[] args) throws ParseException {
		Timer timer=new Timer();
		TimerTaskDemo1 ttd=new TimerTaskDemo1(11);
		//1秒后执行，每隔2秒重复执行
		timer.schedule(ttd, 1000,5000);
		
		Timer timer2=new Timer();
		TimerTaskDemo1 ttd2=new TimerTaskDemo1(11);
		//1秒后执行，每隔2秒重复执行
		timer2.scheduleAtFixedRate(ttd2, 1000, 5000);
		
		//指定时间执行
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d=sdf.parse("2016-12-15 14:00:00");
		timer.schedule(ttd, d, 8000);
		timer2.schedule(ttd2, d, 8000);
		
	}

}
